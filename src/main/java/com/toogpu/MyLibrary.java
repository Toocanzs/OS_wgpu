package com.toogpu;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface MyLibrary extends Library {
    MyLibrary INSTANCE = (MyLibrary) Native.load(
            "wgpu_native",
            MyLibrary.class);

    int wgpuGetVersion();
}
