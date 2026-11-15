# LibOQSTestApp

![KemTest on Android x86_64](https://github.com/Hatzen/LibOQSTestApp/workflows/Test%20Liboqs%20on%20Android/badge.svg?branch=master)

This is an unofficial test app for [open-quantum-safe/liboqs](https://github.com/open-quantum-safe/liboqs).
This app shows how to use `liboqs` (via [open-quantum-safe/liboqs-java](https://github.com/open-quantum-safe/liboqs-java)) on Android devices. Usually you would use the OpenSSL/provider implementation for TLS, but using `liboqs` directly can be useful for application-level end-to-end encryption.

## Project structure

The project is split into:

1) [`liboqs-android`](https://github.com/Hatzen/LibOQSTestApp/tree/master/liboqs-android): Android library module wrapping the JNI interface (`org.openquantumsafe.*` + `de.hartz.software.unofficial.liboqs.android.CommonAndroid`) to use `liboqs` on Android.

2) [`app`](https://github.com/Hatzen/LibOQSTestApp/tree/master/app): Example Android app demonstrating KEM key exchange and symmetric encryption.
<img src="https://user-images.githubusercontent.com/21283655/114078514-53566d00-98a9-11eb-919e-b587c62e41bd.png" height="300">

## Automated build setup (no copied upstream code)

Upstream `liboqs` and `liboqs-java` sources are **not** checked into this repository. Instead, [`liboqs-android/init-prebuilt-liboqs.sh`](liboqs-android/init-prebuilt-liboqs.sh) clones upstream `liboqs-java` and `liboqs`, cross-compiles `liboqs.so` using `liboqs/scripts/build-android.sh`, and places the generated headers, `.so` files, JNI C sources, and Java bindings into gitignored paths inside `liboqs-android/`.

Gradle automatically invokes `initPrebuiltLiboqs` before `preBuild` whenever those generated files are missing, or you can run the script manually:

```bash
# Build all supported Android ABIs (armeabi-v7a, arm64-v8a, x86, x86_64)
./liboqs-android/init-prebuilt-liboqs.sh

# Build only specific ABI(s) for faster local iteration (e.g. emulator or physical device)
ABIS="arm64-v8a" ./liboqs-android/init-prebuilt-liboqs.sh
# Or pass via Gradle property:
./gradlew assembleDebug -Pabis=arm64-v8a
```

Requirements:
- Git, Bash (Git Bash on Windows), CMake (from PATH or Android SDK `cmake/`), Ninja (optional, auto-detected), and an Android NDK (detected from `ANDROID_NDK_HOME`, `ANDROID_NDK_ROOT`, `local.properties`, or Android SDK `ndk/`; downloaded automatically on Linux CI if missing).
- Supported ABIs: `armeabi-v7a`, `arm64-v8a`, `x86`, and `x86_64` (minimum Android SDK level 21).

## Important notes for Android usage

- **Thread stack size (e.g. Classic McEliece)**: Several post-quantum algorithms allocate large buffers on the C call stack, which can exceed Android's default thread stack size and cause `SIGSEGV`. Run key generation / encapsulation / signing on a `Thread` created with a larger stack size (for example `8L * 1024 * 1024` bytes), as shown in `MainActivity.java`, `KEMTest.java`, and `SignatureTest.java`.
- **Native library loading**: Call `CommonAndroid.loadNativeLibrary()` before accessing `org.openquantumsafe.*` classes so `liboqs` and `liboqs-jni` are loaded in order.

## Modifying `liboqs` or `liboqs-java` C code (local development)

`init-prebuilt-liboqs.sh` clones into `liboqs-android/prebuild-liboqs/` (`liboqs` and `liboqs-java`) and **preserves existing clones** unless `--clean` / `-c` is passed:

1. Run `./liboqs-android/init-prebuilt-liboqs.sh` once to clone and build upstream sources.
2. **Modifying `liboqs` C code**: Edit files inside `liboqs-android/prebuild-liboqs/liboqs/`, then re-run `./liboqs-android/init-prebuilt-liboqs.sh` (or `ABIS="arm64-v8a" ./liboqs-android/init-prebuilt-liboqs.sh` for fast single-ABI rebuilds). It keeps your local changes, recompiles `liboqs.so`, and updates `liboqs-android/jni/include/oqs` and `liboqs-android/jni/jniLibs`.
3. **Modifying `liboqs-java` JNI C/Java code**:
   - Edit `liboqs-android/prebuild-liboqs/liboqs-java/src/main/{c,java}` and re-run `init-prebuilt-liboqs.sh`, **or**
   - For quick JNI-only edits in Android Studio, edit the copied C files directly in `liboqs-android/jni/jni/` (note: running `init-prebuilt-liboqs.sh` again will re-sync `jni/jni/` from `prebuild-liboqs/liboqs-java/`).
4. To reset back to clean upstream clones, run `./liboqs-android/init-prebuilt-liboqs.sh --clean`.

## OpenSSL

In some cases it might be a better solution to use the OpenSSL implementation.
Using it globally for all applications requires rooting the device and specific configurations.
Using it only for your own Android application requires further integration, and is possible:
https://stackoverflow.com/questions/3046573/how-to-use-openssl-library-in-the-android-application
https://proandroiddev.com/tutorial-compile-openssl-to-1-1-1-for-android-application-87137968fee
https://stackoverflow.com/a/54053709/8524651
https://wiki.openssl.org/index.php/Android
