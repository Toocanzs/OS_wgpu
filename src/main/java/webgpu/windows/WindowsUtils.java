package webgpu.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.ContainableFrame;
import net.runelite.rlawt.AWTContext;
import org.bridj.JNI;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import webgpu.WGPUSurfaceDescriptorFromWindowsHWND;
import webgpu.WebgpuLibrary;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WindowsUtils {

    public static WGPUSurfaceDescriptorFromWindowsHWND getWindowsSurfaceDescriptor(ClientUI ui, AWTContext context) {

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

        { // Set HWND
            WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(frame));
            long hwndPointerValue = com.sun.jna.Pointer.nativeValue(hwnd.getPointer());
            System.out.println("hwndPointerValue:" + hwndPointerValue);

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

    public static WGPUSurfaceDescriptorFromWindowsHWND getWindowsSurfaceDescriptorCanvas(Canvas canvas) {

        WGPUSurfaceDescriptorFromWindowsHWND result = new WGPUSurfaceDescriptorFromWindowsHWND();

        { // Set HWND
            WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(canvas));
            long hwndPointerValue = com.sun.jna.Pointer.nativeValue(hwnd.getPointer());
            System.out.println("hwndPointerValue:" + hwndPointerValue);

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
