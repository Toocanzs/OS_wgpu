/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2021, 117 <https://twitter.com/117scape>
 * Copyright (c) 2024, Toocanzs <https://github.com/Toocanzs>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.toogpu;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.hooks.DrawCallbacks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.callback.ClientThread;
import javax.swing.SwingUtilities;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.ui.ClientUI;
import net.runelite.rlawt.AWTContext;
import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.Callback;
import org.lwjgl.system.Configuration;
import java.awt.Canvas;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import wgpu.*;
import wgpu.windows.WindowsUtils;

import static org.lwjgl.opengl.GL11C.glBindTexture;
import static org.lwjgl.opengl.GL43C.*;
import static wgpu.WgpuLibrary.*;

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
	@Inject
	private ClientUI clientUI;

	public GLCapabilities glCaps;

	private Canvas canvas;
	private AWTContext awtContext;
	private boolean lwjglInitialized;
	private Callback debugCallback;

	public InterfaceStuff interfaceStuff;
	private long lastFrameTimeMillis;

	private class InterfaceStuff {
		private int pixelBufferObject;
		private int texture;

		private int canvasWidth;
		private int canvasHeight;
		private int vertexArrayObject;
		private int vertexBufferObject;

		public InterfaceStuff() {
			pixelBufferObject = glGenBuffers();
			texture = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, texture);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glBindTexture(GL_TEXTURE_2D, 0);

			vertexArrayObject = glGenVertexArrays();
			vertexBufferObject = glGenBuffers();

			glBindVertexArray(vertexArrayObject);
			FloatBuffer vboUiData = BufferUtils.createFloatBuffer(5 * 4)
					.put(new float[] {
							// vertices, UVs
							1, 1, 0, 1, 0, // top right
							1, -1, 0, 1, 1, // bottom right
							-1, -1, 0, 0, 1, // bottom left
							-1, 1, 0, 0, 0  // top left
					})
					.flip();
			glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
			glBufferData(GL_ARRAY_BUFFER, vboUiData, GL_STATIC_DRAW);

			// position attribute
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0);
			glEnableVertexAttribArray(0);

			// texture coord attribute
			glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
			glEnableVertexAttribArray(1);
			glBindVertexArray(0);
		}

		public void destroy() {
			glDeleteBuffers(pixelBufferObject);
			pixelBufferObject = 0;

			glDeleteTextures(texture);
			texture = 0;

			glDeleteVertexArrays(vertexArrayObject);
			vertexArrayObject = 0;

			glDeleteBuffers(vertexBufferObject);
			vertexBufferObject = 0;
		}

		public void copyInterfaceTextureToGpu(int canvasWidth, int canvasHeight) {
			if (canvasWidth != this.canvasWidth || canvasHeight != this.canvasHeight) {
				this.canvasWidth  = canvasWidth;
				this.canvasHeight = canvasHeight;

				glBindBuffer(GL_PIXEL_UNPACK_BUFFER, pixelBufferObject);
				glBufferData(GL_PIXEL_UNPACK_BUFFER, canvasWidth * canvasHeight * 4L, GL_STREAM_DRAW);
				glBindBuffer(GL_PIXEL_UNPACK_BUFFER, 0);

				glBindTexture(GL_TEXTURE_2D, texture);
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, canvasWidth, canvasHeight, 0, GL_BGRA, GL_UNSIGNED_BYTE, 0);
				glBindTexture(GL_TEXTURE_2D, 0);
			}
			final BufferProvider bufferProvider = client.getBufferProvider();
			final int[] pixels = bufferProvider.getPixels();
			final int width = bufferProvider.getWidth();
			final int height = bufferProvider.getHeight();

			glBindBuffer(GL_PIXEL_UNPACK_BUFFER, pixelBufferObject);
			ByteBuffer mappedBuffer = glMapBuffer(GL_PIXEL_UNPACK_BUFFER, GL_WRITE_ONLY);
			if (mappedBuffer == null) {
				log.error("Unable to map interface PBO. Skipping UI...");
			} else {
				mappedBuffer.asIntBuffer().put(pixels, 0, width * height);
				glUnmapBuffer(GL_PIXEL_UNPACK_BUFFER);
				glBindTexture(GL_TEXTURE_2D, texture);
				// NOTE: with an unpack buffer bound pixels(last param) is treated as an offset into the unpack buffer
				glTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, width, height, GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, 0);
			}
			glBindBuffer(GL_PIXEL_UNPACK_BUFFER, 0);
			glBindTexture(GL_TEXTURE_2D, 0);
		}
	}

	private int interfaceProgram; // TODO: Refactor

	private static String pointerToString(Pointer<Byte> b) {
		return b.getString(Pointer.StringType.C);
	}

	public class AdapterRequester extends WGPURequestAdapterCallback {

		@Override
		public void apply(IntValuedEnum<WGPURequestAdapterStatus> status, WGPUAdapter adapter, Pointer<Byte> message, Pointer<?> userdata) {
			WGPUAdapterProperties properties = new WGPUAdapterProperties();
			wgpuAdapterGetProperties(adapter, Pointer.getPointer(properties));

			log.info(String.format("Adapter: %s", pointerToString(properties.name())));
			log.info(String.format("\tVendor Name: %s", pointerToString(properties.vendorName())));
			log.info(String.format("\tVendor Id: %d", properties.vendorID()));
			log.info(String.format("\tArchitecture: %s", pointerToString(properties.architecture())));
			log.info(String.format("\tVendor Name: %d", properties.deviceID()));
			log.info(String.format("\tType: %s", properties.adapterType().toString()));
			log.info(String.format("\tChosen Backend: %s", properties.backendType().toString()));
		}
	}

	AdapterRequester adapterRequester = new AdapterRequester();

	@Override
	protected void startUp()
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

				int version = MyLibrary.INSTANCE.wgpuGetVersion();
				log.info("wgpu VERSION: " + version);

				canvas.setIgnoreRepaint(true);

				{
					String dllPath = TooGpuPlugin.class.getClassLoader().getResource("win32-x86-64/wgpu_native.dll").getPath();
					File dllFile = new File(dllPath);
					BridJ.setNativeLibraryFile("wgpu", dllFile);
					BridJ.register();

					log.info("Loaded wgpu. Version:" + wgpuGetVersion());

					WGPUInstanceDescriptor descriptor = new WGPUInstanceDescriptor();
					WgpuLibrary.WGPUInstance instance = wgpuCreateInstance(Pointer.getPointer(descriptor));

					log.info("got instance? " + (instance.get() != null));
					WGPUSurfaceDescriptorFromWindowsHWND windowsDescriptor = WindowsUtils.getWindowsSurfaceDescriptor(clientUI);
					WGPUSurfaceDescriptor surfaceDescriptor = new WGPUSurfaceDescriptor();

					surfaceDescriptor.nextInChain(Pointer.getPointer(windowsDescriptor).as(WGPUChainedStruct.class));
					WGPUSurface surface = wgpuInstanceCreateSurface(instance, Pointer.getPointer(surfaceDescriptor));
					log.info("created surface");

					log.info("Requesting adapters...");
					WGPURequestAdapterOptions options = new WGPURequestAdapterOptions();
					wgpuInstanceRequestAdapter(instance, Pointer.getPointer(options), adapterRequester.toPointer(), Pointer.NULL);
					log.info("Requested adapters");

					wgpuSurfaceRelease(surface);
					wgpuInstanceRelease(instance);
					if(true) return true;

				}

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

				List<String> fallbackDevices = List.of(
						"GDI Generic",
						"D3D12 (Microsoft Basic Render Driver)",
						"softpipe"
				);
				boolean isFallbackGpu = fallbackDevices.contains(glRenderer);
				boolean isUnsupportedGpu = isFallbackGpu || (!glCaps.OpenGL43);
				if (isUnsupportedGpu) {
					log.error(
							"The GPU is lacking OpenGL 4.3 support. Stopping the plugin..."
					);
					// TODO: displayUnsupportedGpuMessage(isFallbackGpu, glRenderer);
					stopPlugin();
					return true;
				}

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

				setupSyncMode();

				interfaceStuff = new InterfaceStuff();

				{ // TODO: refactor
					String vertexSource = null;
					String fragmentSource = null;
					String shaderPathString = System.getenv().get("TOOGPU_SHADER_PATH");
					assert shaderPathString != null && !shaderPathString.equals("");
					//TODO: Paths.get(TooGpuPlugin.class)
					try {
						vertexSource = Files.readString(Paths.get(shaderPathString, "ui/ui_vert.glsl"));
						fragmentSource = Files.readString(Paths.get(shaderPathString, "ui/ui_frag.glsl"));
					} catch (IOException e) {
						log.error(e.toString());
						stopPlugin();
						return true;
					}
					assert vertexSource != null;
					assert fragmentSource != null;

					int vertexShader = glCreateShader(GL_VERTEX_SHADER);
					glShaderSource(vertexShader, vertexSource);
					boolean compiledVertex = compileShader(vertexShader);
					if (!compiledVertex) {
						glDeleteShader(vertexShader);
						stopPlugin();
						return true;
					}

					int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

					glShaderSource(fragmentShader, fragmentSource);
					boolean compiledFragment = compileShader(fragmentShader);
					if (!compiledFragment) {
						glDeleteShader(fragmentShader);
						stopPlugin();
						return true;
					}

					interfaceProgram = glCreateProgram();

					glAttachShader(interfaceProgram, vertexShader);
					glAttachShader(interfaceProgram, fragmentShader);
					boolean linked = linkProgram(interfaceProgram);
					if (!linked) {
						// linkProgram deletes programs for us if they fail
						glDeleteShader(vertexShader);
						glDeleteShader(fragmentShader);
						stopPlugin();
						return true;
					}

					glDeleteShader(vertexShader);
					glDeleteShader(fragmentShader);

					int uniUiTexture = glGetUniformLocation(interfaceProgram, "ui_texture"); // TODO: remove
					glUseProgram(interfaceProgram);
					glUniform1i(uniUiTexture, 0); // Set to texture unit 0
					glUseProgram(0);
				}

				lastFrameTimeMillis = System.currentTimeMillis();

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

	boolean linkProgram(int program) { // TODO: Refactor
		// NOTE: Assumes that you've already attached shaders to this program (glAttachShader)
		glLinkProgram(interfaceProgram);
		int[] isLinked = new int[1];
		glGetProgramiv(program, GL_LINK_STATUS, isLinked);
		if (isLinked[0] == GL_FALSE) {
			String errorLog = glGetProgramInfoLog(program);
			log.error("Error linking program: " + errorLog);
			glDeleteProgram(program);
			return false;
		}
		return true;
	}

	boolean compileShader(int shader) { // TODO: Refactor
		// NOTE: assumes you've already attached shader source to this shader (glShaderSource)
		glCompileShader(shader);
		int[] isCompiled = new int[1];
		glGetShaderiv(shader, GL_COMPILE_STATUS, isCompiled);
		if(isCompiled[0] == GL_FALSE)
		{
			String errorLog = glGetShaderInfoLog(shader);
			log.error("Error compiling shader:" + errorLog);
			glDeleteShader(shader);
			return false;
		}
		return true;
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

				if (interfaceStuff != null) interfaceStuff.destroy();

				glDeleteProgram(interfaceProgram);

				/*textureManager.shutDown();

				destroyBuffers();

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

	private void setupSyncMode()
	{
		final boolean unlockFps = config.unlockFps();
		client.setUnlockedFps(unlockFps);

		int swapInterval;

		if (unlockFps) {
			swapInterval = -1;
		} else {
			swapInterval = 0;
		}

		int actualSwapInterval = awtContext.setSwapInterval(swapInterval);
		if (actualSwapInterval != swapInterval) {
			log.info("unsupported swap interval {}, got {}", swapInterval, actualSwapInterval);
		}

		client.setUnlockedFpsTarget(actualSwapInterval == 0 ? config.fpsTarget() : 0);
		checkGLErrors();
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

	public void restartPlugin() {
		// For some reason, it's necessary to delay this like below to prevent the canvas from locking up on Linux
		SwingUtilities.invokeLater(() -> clientThread.invokeLater(() -> {
			shutDown();
			clientThread.invokeLater(this::startUp);
		}));
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
		final GameState gameState = client.getGameState();
		if (gameState == GameState.STARTING) {
			return;
		}

		float deltaTime = (float) ((System.currentTimeMillis() - lastFrameTimeMillis) / 1000.);

		glClearColor(0f,0f,0f,1f); // TODO: Fog color
		glClearDepthf(0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		final int canvasWidth = client.getCanvasWidth();
		final int canvasHeight = client.getCanvasHeight();

		try {
			interfaceStuff.copyInterfaceTextureToGpu(canvasWidth, canvasHeight);
		} catch (Exception ex) {
			// Fixes: https://github.com/runelite/runelite/issues/12930
			// Gracefully Handle loss of opengl buffers and context
			log.warn("copyInterfaceTextureToGlTexture exception", ex);
			restartPlugin();
			return;
		}

		{ // Draw ui // TODO: Refactor
			glEnable(GL_BLEND);
			glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

			// Set interface texture to unit 0
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, interfaceStuff.texture);

			glUseProgram(interfaceProgram);
			glBindVertexArray(interfaceStuff.vertexArrayObject);
			glDrawArrays(GL_TRIANGLE_FAN, 0, 4);

			glBindVertexArray(0);
			glUseProgram(0);
			glBindTexture(GL_TEXTURE_2D, 0);

			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glDisable(GL_BLEND);
		}

		try {
			awtContext.swapBuffers();
			//drawManager.processDrawComplete(this::screenshot);
		} catch (RuntimeException ex) {
			// this is always fatal
			if (!canvas.isValid()) {
				// this might be AWT shutting down on VM shutdown, ignore it
				return;
			}

			log.error("Unable to swap buffers:", ex);
		}

		glBindFramebuffer(GL_FRAMEBUFFER, awtContext.getFramebuffer(false));

		checkGLErrors();
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
		return true;
	}
}
