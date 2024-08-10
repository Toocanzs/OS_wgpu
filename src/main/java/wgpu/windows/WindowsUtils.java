package wgpu.windows;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.ContainableFrame;
import org.bridj.JNI;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import wgpu.WGPUChainedStruct;
import wgpu.WGPUSurfaceDescriptorFromWindowsHWND;
import wgpu.WgpuLibrary;

import java.lang.reflect.Field;

public class WindowsUtils {

    public static WGPUSurfaceDescriptorFromWindowsHWND getWindowsSurfaceDescriptor(ClientUI ui) {

        // Grab the frame from the client UI
        ContainableFrame frame;
        try {
            Field privateField = ClientUI.class.getDeclaredField("frame");
            privateField.setAccessible(true);
            frame = (ContainableFrame )privateField.get(ui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assert frame != null;

        WGPUSurfaceDescriptorFromWindowsHWND result = new WGPUSurfaceDescriptorFromWindowsHWND();
        // Frame pointer is an HWND
        Pointer<?> framePointer = JNI.getGlobalPointer(frame);
        result.hwnd(framePointer);

        Kernel32 kernel32 = Kernel32.INSTANCE;
        WinDef.HMODULE hmodule = kernel32.GetModuleHandle("");
        long hmodulePointerLong = hmodule == null ? 0 : com.sun.jna.Pointer.nativeValue(hmodule.getPointer());

        result.hinstance(Pointer.pointerToAddress(hmodulePointerLong, (PointerIO<?>) null));

        WGPUChainedStruct chainedStruct = new WGPUChainedStruct();
        chainedStruct.sType(WgpuLibrary.WGPUSType.WGPUSType_SurfaceDescriptorFromWindowsHWND);
        result.chain(chainedStruct);

        return result;
    }
}
