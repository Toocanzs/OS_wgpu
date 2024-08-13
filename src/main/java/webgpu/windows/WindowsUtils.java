package webgpu.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import org.bridj.Pointer;
import webgpu.WGPUSurfaceDescriptorFromWindowsHWND;
import webgpu.WebgpuLibrary;

import java.awt.*;

public class WindowsUtils {

    public static WGPUSurfaceDescriptorFromWindowsHWND getWindowsSurfaceDescriptorCanvas(Canvas canvas) {

        WGPUSurfaceDescriptorFromWindowsHWND result = new WGPUSurfaceDescriptorFromWindowsHWND();

        { // Set HWND
            WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(canvas));
            long hwndPointerValue = com.sun.jna.Pointer.nativeValue(hwnd.getPointer());

            result.hwnd(Pointer.pointerToAddress(hwndPointerValue));
        }

        { // Set HINSTANCE
            Kernel32 kernel32 = Kernel32.INSTANCE;
            WinDef.HMODULE hmodule = kernel32.GetModuleHandle(null);
            var hinstance = (WinDef.HINSTANCE)(hmodule);
            long hinstancePtrValue = com.sun.jna.Pointer.nativeValue(hinstance.getPointer());
            result.hinstance(Pointer.pointerToAddress(hinstancePtrValue));
        }

        result.chain().sType(WebgpuLibrary.WGPUSType.WGPUSType_SurfaceDescriptorFromWindowsHWND);
        return result;
    }
}
