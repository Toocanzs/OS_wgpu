package com.toogpu;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.hooks.DrawCallbacks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.callback.ClientThread;
import javax.swing.SwingUtilities;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.rlawt.AWTContext;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.Callback;
import org.lwjgl.system.Configuration;

import java.awt.*;

import static org.lwjgl.opengl.GL43C.*;

@Slf4j
@PluginDescriptor(
	name = "TooGPU",
		description = "GPU renderer",
		tags = { "gpu", "renderer", "graphics" },
		conflicts = "GPU"
)
public class TooGpuPlugin extends Plugin implements DrawCallbacks
{
	@Inject
	private Client client;

	@Inject
	private TooGpuConfig config;
	@Inject
	private ClientThread clientThread;
	@Inject
	private PluginManager pluginManager;

	public GLCapabilities glCaps;

	private Canvas canvas;
	private AWTContext awtContext;
	private boolean lwjglInitialized;
	private Callback debugCallback;

	@Override
	protected void startUp() throws Exception
	{
		clientThread.invoke(() -> {
			try {
				AWTContext.loadNatives();
				canvas = client.getCanvas();

				synchronized (canvas.getTreeLock()) {
					// Delay plugin startup until the client's canvas is valid
					if (!canvas.isValid())
						return false;

					awtContext = new AWTContext(canvas);
					awtContext.configurePixelFormat(0, 0, 0);
				}

				awtContext.createGLContext();

				canvas.setIgnoreRepaint(true);

				// lwjgl defaults to lwjgl- + user.name, but this breaks if the username would cause an invalid path
				// to be created.
				Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.set("lwjgl-rl");

				glCaps = GL.createCapabilities();

				String glRenderer = glGetString(GL_RENDERER);
				String arch = System.getProperty("sun.arch.data.model", "Unknown");
				if (glRenderer == null)
					glRenderer = "Unknown";
				log.info("Using device: {}", glRenderer);
				log.info("Using driver: {}", glGetString(GL_VERSION));
				log.info("Client is {}-bit", arch);

				/*computeMode = OSType.getOSType() == OSType.MacOS ? ComputeMode.OPENCL : ComputeMode.OPENGL;

				List<String> fallbackDevices = List.of(
						"GDI Generic",
						"D3D12 (Microsoft Basic Render Driver)",
						"softpipe"
				);
				boolean isFallbackGpu = fallbackDevices.contains(glRenderer) && !Props.has("rlhd.allowFallbackGpu");
				boolean isUnsupportedGpu = isFallbackGpu || (computeMode == ComputeMode.OPENGL ? !glCaps.OpenGL43 : !glCaps.OpenGL31);
				if (isUnsupportedGpu) {
					log.error(
							"The GPU is lacking OpenGL {} support. Stopping the plugin...",
							computeMode == ComputeMode.OPENGL ? "4.3" : "3.1"
					);
					displayUnsupportedGpuMessage(isFallbackGpu, glRenderer);
					stopPlugin();
					return true;
				}*/

				lwjglInitialized = true;
				checkGLErrors();

				if (log.isDebugEnabled() && glCaps.glDebugMessageControl != 0) {
					debugCallback = GLUtil.setupDebugMessageCallback();
					if (debugCallback != null) {
						//	GLDebugEvent[ id 0x20071
						//		type Warning: generic
						//		severity Unknown (0x826b)
						//		source GL API
						//		msg Buffer detailed info: Buffer object 11 (bound to GL_ARRAY_BUFFER_ARB, and GL_SHADER_STORAGE_BUFFER (4), usage hint is GL_STREAM_DRAW) will use VIDEO memory as the source for buffer object operations.
						glDebugMessageControl(GL_DEBUG_SOURCE_API, GL_DEBUG_TYPE_OTHER,
								GL_DONT_CARE, 0x20071, false
						);

						//	GLDebugMessageHandler: GLDebugEvent[ id 0x20052
						//		type Warning: implementation dependent performance
						//		severity Medium: Severe performance/deprecation/other warnings
						//		source GL API
						//		msg Pixel-path performance warning: Pixel transfer is synchronized with 3D rendering.
						glDebugMessageControl(GL_DEBUG_SOURCE_API, GL_DEBUG_TYPE_PERFORMANCE,
								GL_DONT_CARE, 0x20052, false
						);
					}
				}

				client.setDrawCallbacks(this);
				client.setGpuFlags(
						DrawCallbacks.GPU |
								DrawCallbacks.HILLSKEW |
								DrawCallbacks.NORMALS |
								DrawCallbacks.NO_VERTEX_SNAPPING
				);
				client.setExpandedMapLoading(config.expandedMapLoadingChunks());
				// force rebuild of main buffer provider to enable alpha channel
				client.resizeCanvas();

				// We need to force the client to reload the scene since we're changing GPU flags
				if (client.getGameState() == GameState.LOGGED_IN)
					client.setGameState(GameState.LOADING);

				checkGLErrors();
			}
			catch (Throwable err)
			{
				log.error("Error while starting TooGPU", err);
				stopPlugin();
			}
			return true;
		});
	}

	public void checkGLErrors() {
		if (!log.isDebugEnabled())
			return;

		while (true) {
			int err = glGetError();
			if (err == GL_NO_ERROR)
				return;

			String errStr;
			switch (err) {
				case GL_INVALID_ENUM:
					errStr = "INVALID_ENUM";
					break;
				case GL_INVALID_VALUE:
					errStr = "INVALID_VALUE";
					break;
				case GL_INVALID_OPERATION:
					errStr = "INVALID_OPERATION";
					break;
				case GL_INVALID_FRAMEBUFFER_OPERATION:
					errStr = "INVALID_FRAMEBUFFER_OPERATION";
					break;
				default:
					errStr = String.valueOf(err);
					break;
			}

			log.debug("glGetError:", new Exception(errStr));
		}
	}

	@Override
	protected void shutDown()
	{
		clientThread.invoke(() -> {
			var scene = client.getScene();
			if (scene != null)
				scene.setMinLevel(0);

			client.setGpuFlags(0);
			client.setDrawCallbacks(null);
			client.setUnlockedFps(false);
			client.setExpandedMapLoading(0);

			/*developerTools.deactivate();
			modelPusher.shutDown();
			tileOverrideManager.shutDown();
			groundMaterialManager.shutDown();
			modelOverrideManager.shutDown();
			lightManager.shutDown();
			environmentManager.shutDown();
			fishingSpotReplacer.shutDown();
			areaManager.shutDown();*/

			if (lwjglInitialized) {
				lwjglInitialized = false;
				waitUntilIdle();

				/*textureManager.shutDown();

				destroyBuffers();
				destroyInterfaceTexture();
				destroyPrograms();
				destroyVaos();
				destroySceneFbo();
				destroyShadowMapFbo();
				destroyTileHeightMap();
				destroyModelSortingBins();

				openCLManager.shutDown();*/
			}

			if (awtContext != null)
				awtContext.destroy();
			awtContext = null;

			if (debugCallback != null)
				debugCallback.free();
			debugCallback = null;

			/*if (sceneContext != null)
				sceneContext.destroy();
			sceneContext = null;

			synchronized (this) {
				if (nextSceneContext != null)
					nextSceneContext.destroy();
				nextSceneContext = null;
			}

			if (modelPassthroughBuffer != null)
				modelPassthroughBuffer.destroy();
			modelPassthroughBuffer = null;*/

			// force main buffer provider rebuild to turn off alpha channel
			client.resizeCanvas();

			// Force the client to reload the scene to reset any scene modifications we may have made
			if (client.getGameState() == GameState.LOGGED_IN)
				client.setGameState(GameState.LOADING);
		});
	}

	private void waitUntilIdle() {
		//if (computeMode == ComputeMode.OPENCL)
		//	openCLManager.finish();
		glFinish();
	}

	public void stopPlugin()
	{
		SwingUtilities.invokeLater(() ->
		{
			try
			{
				pluginManager.setPluginEnabled(this, false);
				pluginManager.stopPlugin(this);
			}
			catch (PluginInstantiationException ex)
			{
				log.error("Error while stopping TooGPU:", ex);
			}
		});

		shutDown();
	}

	@Provides
	TooGpuConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TooGpuConfig.class);
	}

	@Override
	public void draw(Projection projection, Scene scene, Renderable renderable, int orientation, int x, int y, int z, long hash) {

	}

	@Override
	public void drawScenePaint(Scene scene, SceneTilePaint paint, int plane, int tileX, int tileZ) {

	}

	@Override
	public void drawSceneTileModel(Scene scene, SceneTileModel model, int tileX, int tileZ) {

	}

	@Override
	public void draw(int overlayColor) {

	}

	@Override
	public void drawScene(double cameraX, double cameraY, double cameraZ, double cameraPitch, double cameraYaw, int plane) {

	}

	@Override
	public void postDrawScene() {

	}

	@Override
	public void animate(Texture texture, int diff) {

	}

	@Override
	public void loadScene(Scene scene) {

	}

	@Override
	public void swapScene(Scene scene) {

	}

	@Override
	public boolean tileInFrustum(Scene scene, int pitchSin, int pitchCos, int yawSin, int yawCos, int cameraX, int cameraY, int cameraZ, int plane, int msx, int msy) {
		return DrawCallbacks.super.tileInFrustum(scene, pitchSin, pitchCos, yawSin, yawCos, cameraX, cameraY, cameraZ, plane, msx, msy);
	}
}
