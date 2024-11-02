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
import org.bridj.BridJ;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
//import org.lwjgl.BufferUtils;
//import org.lwjgl.opengl.GLCapabilities;
//import org.lwjgl.system.Callback;

import java.awt.Canvas;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import webgpu.*;
import webgpu.windows.WindowsUtils;

//import static org.lwjgl.opengl.GL11C.glBindTexture;
//import static org.lwjgl.opengl.GL43C.*;
import static webgpu.WebgpuLibrary.*;

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

	//public GLCapabilities glCaps;

	private Canvas canvas;
	private boolean webgpuInitialized;

	public InterfaceStuff interfaceStuff;
	private long lastFrameTimeMillis;
	private WGPUAdapter adapter;
	private SurfaceStuff surfaceStuff;

	static {
		BridJ.setNativeLibraryActualName("webgpu", "webgpu_dawn.dll");
		BridJ.addLibraryPath(TooGpuPlugin.class.getClassLoader().getResource("win32-x86-64").getPath());
		BridJ.register();
	}

	private class SurfaceStuff {
		public WGPUSurface surface;
		public int surfaceWidth;
		public int surfaceHeight;

		public SurfaceStuff(WGPUInstance instance, Canvas canvas) {
			surfaceHeight = 0;
			surfaceWidth = 0;
			WGPUSurfaceDescriptorFromWindowsHWND windowsDescriptor = WindowsUtils.getWindowsSurfaceDescriptorCanvas(canvas);
			WGPUSurfaceDescriptor surfaceDescriptor = new WGPUSurfaceDescriptor();
			surfaceDescriptor.nextInChain(Pointer.getPointer(windowsDescriptor).as(WGPUChainedStruct.class));

			surface = wgpuInstanceCreateSurface(instance, Pointer.getPointer(surfaceDescriptor));
		}

		public void configureSurface(WGPUDevice device, WGPUAdapter adapter, int canvasWidth, int canvasHeight, boolean forceReconfigure) {
			boolean changedSize = surfaceWidth != canvasWidth || surfaceHeight != canvasHeight;
			if (changedSize || forceReconfigure) {
				WGPUSurfaceCapabilities capabilities = new WGPUSurfaceCapabilities();
				wgpuSurfaceGetCapabilities(surface, adapter, Pointer.getPointer(capabilities));
				IntValuedEnum<WGPUTextureFormat> preferredFormat = capabilities.formats().get(0);
				log.info("preferredFormat=" + preferredFormat);

				WGPUSurfaceConfiguration configuration = new WGPUSurfaceConfiguration();
				configuration.device(device);
				configuration.format(preferredFormat);
				configuration.usage((int) WGPUTextureUsage_RenderAttachment);
				configuration.viewFormatCount(0);
				configuration.viewFormats((Pointer<IntValuedEnum<WGPUTextureFormat>>)Pointer.NULL);
				configuration.alphaMode(WGPUCompositeAlphaMode.WGPUCompositeAlphaMode_Auto);
				configuration.width(canvasWidth);
				surfaceWidth = canvasWidth;
				configuration.height(canvasHeight);
				surfaceHeight = canvasHeight;
				final boolean unlockFps = config.unlockFps();
				configuration.presentMode(unlockFps ? WGPUPresentMode.WGPUPresentMode_Mailbox : WGPUPresentMode.WGPUPresentMode_Fifo);
				client.setUnlockedFps(unlockFps);
				if (unlockFps) {
					client.setUnlockedFpsTarget(0);
				} else {
					client.setUnlockedFpsTarget(config.fpsTarget());
				}

				wgpuSurfaceConfigure(surface, Pointer.getPointer(configuration));
				log.info("Configured surface");
				// wgpuSurfaceCapabilitiesFreeMembers(capabilities); // NOTE: BridJ can't pass structs by value so this dies? We're leaking memory here.
			}
		}

		public void destroy() {
			if (surface != null) {
				wgpuSurfaceUnconfigure(surface);
				wgpuSurfaceRelease(surface);
			}
			surface = null;
			surfaceHeight = 0;
			surfaceWidth = 0;
		}

	}
	private WGPUInstance instance;
	private WGPUDevice device;
	private WGPUQueue queue;


	private class InterfaceStuff {
		private int pixelBufferObject;
		private int texture;

		private int canvasWidth;
		private int canvasHeight;
		private int vertexArrayObject;
		private int vertexBufferObject;

		public InterfaceStuff() {
			/*pixelBufferObject = glGenBuffers();
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
			glBindVertexArray(0);*/
		}

		public void destroy() {
			/*
			glDeleteBuffers(pixelBufferObject);
			pixelBufferObject = 0;

			glDeleteTextures(texture);
			texture = 0;

			glDeleteVertexArrays(vertexArrayObject);
			vertexArrayObject = 0;

			glDeleteBuffers(vertexBufferObject);
			vertexBufferObject = 0;*/
		}

		public void copyInterfaceTextureToGpu(int canvasWidth, int canvasHeight) {
			/*
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
			 */
		}
	}

	private static String pointerToString(Pointer<Byte> b) {
		return b.getString(Pointer.StringType.C);
	}

	public class AdapterRequester extends WGPURequestAdapterCallback {
		private ArrayList<WGPUAdapter> adapters = new ArrayList<>();
		public ArrayList<WGPUAdapter> getAdapters(WGPUInstance instance) {
			WGPURequestAdapterOptions options = new WGPURequestAdapterOptions();
			options.powerPreference(WGPUPowerPreference.WGPUPowerPreference_HighPerformance);
			wgpuInstanceRequestAdapter(instance, Pointer.getPointer(options), adapterRequester.toPointer(), Pointer.NULL);
			var result = (ArrayList<WGPUAdapter>) adapters.clone();
			adapters.clear();
			return result;
		}
		@Override
		public void apply(IntValuedEnum<WGPURequestAdapterStatus> status, WGPUAdapter adapter, Pointer<Byte> message, Pointer<?> userdata) {

			WGPUAdapterInfo info = new WGPUAdapterInfo();
			wgpuAdapterGetInfo(adapter, Pointer.getPointer(info));

			log.info(String.format("Adapter: %s", pointerToString(info.device())));
			log.info(String.format("\tVendor Name: %s", pointerToString(info.vendor())));
			log.info(String.format("\tVendor Id: %d", info.vendorID()));
			log.info(String.format("\tArchitecture: %s", pointerToString(info.architecture())));
			log.info(String.format("\tDevice Id: %d", info.deviceID()));
			log.info(String.format("\tDescription: %s", pointerToString(info.description())));
			log.info(String.format("\tType: %s", info.adapterType().toString()));
			log.info(String.format("\tBackend Type: %s", info.backendType().toString()));
			log.info("Adapter status: " + status);
			adapters.add(adapter);

			//wgpuAdapterInfoFreeMembers(info); // NOTE: BridJ can't pass structs by value so this dies? We're leaking memory here.
		}
	}

	public class DeviceRequester extends WGPURequestDeviceCallback {
		private WGPUDevice device;
		@Override
		public void apply(IntValuedEnum<WGPURequestDeviceStatus> status, WGPUDevice device, Pointer<Byte> message, Pointer<?> userdata) {
			log.info("Device status: " + status);
			this.device = device;
		}

		public WGPUDevice getDevice(WGPUAdapter adapter) {
			WGPUDeviceDescriptor descriptor = new WGPUDeviceDescriptor();

			WGPUUncapturedErrorCallbackInfo uncapturedErrorCallbackInfo = new WGPUUncapturedErrorCallbackInfo();
			uncapturedErrorCallbackInfo.callback(deviceCallbacker.toPointer());
			descriptor.uncapturedErrorCallbackInfo(uncapturedErrorCallbackInfo);

			wgpuAdapterRequestDevice(adapter, Pointer.getPointer(descriptor), deviceRequester.toPointer(), null);
			return device;
		}
	}

	AdapterRequester adapterRequester = new AdapterRequester();
	DeviceRequester deviceRequester = new DeviceRequester();

	public class LogCallbacker extends WGPULoggingCallback {

		@Override
		public void apply(IntValuedEnum<WGPULoggingType> type, Pointer<Byte> message, Pointer<?> userdata) {
			String messageString = message.getString(Pointer.StringType.C);
			if (type == WGPULoggingType.WGPULoggingType_Error) {
				log.error(String.format("WGPU(%s): %s", type, messageString));
			} else {
				log.info(String.format("WGPU(%s): %s", type, messageString));
			}
		}
	}

	LogCallbacker logCallbacker = new LogCallbacker();

	public class DeviceCallbacker extends WGPUErrorCallback {

		@Override
		public void apply(IntValuedEnum<WGPUErrorType> type, Pointer<Byte> message, Pointer<?> userdata) {
			String messageString = message.getString(Pointer.StringType.C);
			log.error(String.format("WGPU(%s): %s", type, messageString));
		}
	}
	DeviceCallbacker deviceCallbacker = new DeviceCallbacker();

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


			if (webgpuInitialized) {
				webgpuInitialized = false;
				waitUntilIdle();
			}

			if (interfaceStuff != null) interfaceStuff.destroy();

			if (surfaceStuff != null)
				surfaceStuff.destroy();
			surfaceStuff = null;
			if (queue.get() != null)
				wgpuQueueRelease(queue);
			queue = null;
			if (device.get() != null)
				wgpuDeviceRelease(device);
			device = null;
			if (adapter.get() != null)
				wgpuAdapterRelease(adapter);
			adapter = null;

			if (instance.get() != null)
				wgpuInstanceRelease(instance);
			instance = null;

			// force main buffer provider rebuild to turn off alpha channel
			client.resizeCanvas();

			// Force the client to reload the scene to reset any scene modifications we may have made
			if (client.getGameState() == GameState.LOGGED_IN)
				client.setGameState(GameState.LOADING);

			// Since we took over the whole canvas with webgpu, we need to destroy and recreate the native screen resources
			canvas.removeNotify();
			canvas.addNotify();
		});
	}

	@Override
	protected void startUp()
	{
		clientThread.invoke(() -> {
			try {
				canvas = client.getCanvas();

				synchronized (canvas.getTreeLock()) {
					// Delay plugin startup until the client's canvas is valid
					if (!canvas.isValid())
						return false;
				}

				canvas.setIgnoreRepaint(true);

				WGPUInstanceDescriptor descriptor = new WGPUInstanceDescriptor();

				// Dawn specific stuff to make callbacks occur as soon as an error occurs instead of on wgpuDeviceTick
				Pointer<Byte> toggleName = Pointer.pointerToCString("enable_immediate_error_handling");
				WGPUDawnTogglesDescriptor toggles = new WGPUDawnTogglesDescriptor();
				toggles.chain().next((Pointer<WGPUChainedStruct>) Pointer.NULL);
				toggles.chain().sType(WGPUSType.WGPUSType_DawnTogglesDescriptor);
				toggles.disabledToggleCount(0);
				toggles.enabledToggleCount(1);
				toggles.enabledToggles(Pointer.pointerToPointer(toggleName));

				instance = wgpuCreateInstance(Pointer.getPointer(descriptor));
				toggleName.release();
				if (instance.get() == null) {
					log.error("Unable to get instance");
					return true;
				}

				surfaceStuff = new SurfaceStuff(instance, canvas);
				if (surfaceStuff.surface.get() == null) {
					log.error("Unable to get surface");
					return true;
				}
				log.info("created surface");

				ArrayList<WGPUAdapter> adapters = adapterRequester.getAdapters(instance);
				if (adapters.size() <= 0) {
					log.error("Unable to get adapters");
					return true;
				}

				for(WGPUAdapter adapter : adapters) {
					this.adapter = adapter;
					break; // Just grab the first one for now
				}
				log.info("Got adapter");

				/*WGPUSurfaceCapabilities capabilities = new WGPUSurfaceCapabilities();
				wgpuSurfaceGetCapabilities(surfaceStuff.surface, adapter, Pointer.getPointer(capabilities));
				log.info("created wgpuSurfaceGetCapabilities");

				log.info("formatCount: " + capabilities.formatCount());
				log.info("presentModeCount: " + capabilities.presentModeCount());
				log.info("alphaModeCount: " + capabilities.alphaModeCount());

				for(int i = 0; i < capabilities.formatCount(); i++) {
					var format = capabilities.formats().get(i);
					log.info("we got a format yo: " + format);
				}

				for(int i = 0; i < capabilities.alphaModeCount(); i++) {
					var alphaMode = capabilities.alphaModes().get(i);
					log.info("we got a alphaMode yo: " + alphaMode);
				}*/

				device = deviceRequester.getDevice(adapter);
				if (device.get() == null) {
					log.error("Unable to get device");
					return true;
				}
				log.info("Got device");

				wgpuDeviceSetLoggingCallback(device, logCallbacker.toPointer(), Pointer.NULL);
				log.info("Set log callback");

				queue = wgpuDeviceGetQueue(device);
				if (queue.get() == null) {
					log.error("Unable to get queue");
					return true;
				}
				log.info("Got queue");

				if (client.getGameState() == GameState.LOGGED_IN)
					client.setGameState(GameState.LOADING);

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

				webgpuInitialized = true;

				// interfaceStuff = new InterfaceStuff();

				/*
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

				checkGLErrors();*/
				lastFrameTimeMillis = System.currentTimeMillis();
				waitUntilIdle();
			}
			catch (Throwable err)
			{
				log.error("Error while starting TooGPU", err);
				stopPlugin();
			}
			return true;
		});
	}

	/*boolean linkProgram(int program) { // TODO: Refactor
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
	}*/

	private void waitUntilIdle() {
		wgpuDeviceTick(device); // TODO: I don't think this waits properly. We need like GPUFutures or something
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
		if (!webgpuInitialized) return;

		float deltaTime = (float) ((System.currentTimeMillis() - lastFrameTimeMillis) / 1000.);

		final int canvasWidth = client.getCanvasWidth();
		final int canvasHeight = client.getCanvasHeight();

		surfaceStuff.configureSurface(device, adapter, canvasWidth, canvasHeight, false);

		WGPUSurfaceTexture surfaceTexture = new WGPUSurfaceTexture();
		wgpuSurfaceGetCurrentTexture(surfaceStuff.surface, Pointer.getPointer(surfaceTexture));
		if (surfaceTexture.status() == WGPUSurfaceGetCurrentTextureStatus.WGPUSurfaceGetCurrentTextureStatus_Outdated) {
			waitUntilIdle();
			surfaceStuff.destroy();
			surfaceStuff = new SurfaceStuff(instance, canvas);
			log.info("Surface outdated. Recreating swap chain...");
			return;
		} else if (surfaceTexture.status() != WGPUSurfaceGetCurrentTextureStatus.WGPUSurfaceGetCurrentTextureStatus_Success) {
			log.info("surface returned status: " + surfaceTexture.status());
			return;
		}

		WGPUTextureView targetView = getCurrentTextureView(surfaceTexture);

		WGPUCommandEncoder encoder = wgpuDeviceCreateCommandEncoder(device, (Pointer<WGPUCommandEncoderDescriptor>) Pointer.NULL);

		var attachment = new WGPURenderPassColorAttachment();
		attachment.view(targetView);
		attachment.resolveTarget((WGPUTextureView) Pointer.NULL);
		attachment.loadOp(WGPULoadOp.WGPULoadOp_Clear);
		attachment.storeOp(WGPUStoreOp.WGPUStoreOp_Store);
		var clearColor = new WGPUColor();
		clearColor.r(1);
		clearColor.g((System.currentTimeMillis() / 1000.0) % 1);
		clearColor.b(1);
		clearColor.a(1);
		attachment.depthSlice(0xFFFFFFFF);
		attachment.clearValue(clearColor);

		var attachments = Pointer.allocateArray(WGPURenderPassColorAttachment.class, 1);
		attachments.set(0, attachment);

		WGPURenderPassDescriptor renderPassDescriptor = new WGPURenderPassDescriptor();
		renderPassDescriptor.colorAttachmentCount(1);
		renderPassDescriptor.colorAttachments(attachments);
		renderPassDescriptor.depthStencilAttachment((Pointer<WGPURenderPassDepthStencilAttachment>) Pointer.NULL);
		renderPassDescriptor.occlusionQuerySet((WGPUQuerySet) Pointer.NULL);
		renderPassDescriptor.timestampWrites((Pointer<WGPURenderPassTimestampWrites>) Pointer.NULL);

		var pass = wgpuCommandEncoderBeginRenderPass(encoder, Pointer.getPointer(renderPassDescriptor));
		wgpuRenderPassEncoderEnd(pass);
		wgpuRenderPassEncoderRelease(pass);
		attachments.release();


		/*var debugText = Pointer.pointerToCString("testing");
		wgpuCommandEncoderInsertDebugMarker(encoder, debugText);
		wgpuCommandEncoderInsertDebugMarker(encoder, debugText);
		wgpuCommandEncoderInsertDebugMarker(encoder, debugText);
		wgpuCommandEncoderInsertDebugMarker(encoder, debugText);
		debugText.release();*/

		WGPUCommandBuffer commandBuffer = wgpuCommandEncoderFinish(encoder, (Pointer<WGPUCommandBufferDescriptor>) Pointer.NULL);

		var commandArray = Pointer.allocateArray(WGPUCommandBuffer.class, 1);
		commandArray.set(0, commandBuffer);

		wgpuQueueSubmit(queue, 1, commandArray);

		commandArray.release();

		wgpuSurfacePresent(surfaceStuff.surface);

		wgpuCommandEncoderRelease(encoder);
		wgpuCommandBufferRelease(commandBuffer);
		wgpuTextureViewRelease(targetView);

		wgpuDeviceTick(device);



		/*
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

		 */
	}

	private static WGPUTextureView getCurrentTextureView(WGPUSurfaceTexture surfaceTexture) {
		WGPUTextureViewDescriptor viewDescriptor = new WGPUTextureViewDescriptor();
		viewDescriptor.nextInChain((Pointer<WGPUChainedStruct>) Pointer.NULL);
		viewDescriptor.label((Pointer<Byte>) Pointer.NULL);
		viewDescriptor.format(wgpuTextureGetFormat(surfaceTexture.texture()));
		viewDescriptor.dimension(WGPUTextureViewDimension.WGPUTextureViewDimension_2D);
		viewDescriptor.baseMipLevel(0);
		viewDescriptor.mipLevelCount(1);
		viewDescriptor.baseArrayLayer(0);
		viewDescriptor.arrayLayerCount(1);
		viewDescriptor.aspect(WGPUTextureAspect.WGPUTextureAspect_All);
		WGPUTextureView targetView = wgpuTextureCreateView(surfaceTexture.texture(), Pointer.getPointer(viewDescriptor));
		return targetView;
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
