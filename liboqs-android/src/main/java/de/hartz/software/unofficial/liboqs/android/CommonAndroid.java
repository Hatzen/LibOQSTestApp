package de.hartz.software.unofficial.liboqs.android;

import android.os.Build;
import android.util.Log;

import java.util.Arrays;

/**
 * Android-specific native library loader for liboqs and liboqs-jni.
 */
public final class CommonAndroid {

    private static final String TAG = "liboqs-android";
    private static boolean loaded = false;

    private CommonAndroid() {
    }

    /**
     * Loads the prebuilt {@code oqs} and JNI {@code oqs-jni} native libraries once per process.
     */
    public static synchronized void loadNativeLibrary() {
        if (loaded) {
            return;
        }
        Log.d(TAG, "Supported ABIs: " + Arrays.toString(Build.SUPPORTED_ABIS));
        System.loadLibrary("oqs");
        System.loadLibrary("oqs-jni");
        loaded = true;
    }
}

