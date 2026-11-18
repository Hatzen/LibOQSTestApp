# LibOQSTestApp

![Test and Release Liboqs Android](https://github.com/Hatzen/LibOQSTestApp/actions/workflows/test.yml/badge.svg?branch=master)

This is an unofficial Android library and test app for [open-quantum-safe/liboqs](https://github.com/open-quantum-safe/liboqs) and [open-quantum-safe/liboqs-java](https://github.com/open-quantum-safe/liboqs-java).
It shows how to compile `liboqs` from source for Android and invoke it via JNI for application-level post-quantum key exchange and signatures.

## Project structure

The project is split into:

1) [`liboqs-android`](https://github.com/Hatzen/LibOQSTestApp/tree/master/liboqs-android): Android library module wrapping the JNI interface (`org.openquantumsafe.*` + `de.hartz.software.unofficial.liboqs.android.CommonAndroid`) and compiling `liboqs-jni.so` against `liboqs.so`.

2) [`app`](https://github.com/Hatzen/LibOQSTestApp/tree/master/app): Example Android app demonstrating KEM key exchange and symmetric encryption.
<img src="https://user-images.githubusercontent.com/21283655/114078514-53566d00-98a9-11eb-919e-b587c62e41bd.png" height="300">

## Build from source (no checked-in `.so` binaries or copied code)

No upstream `liboqs` / `liboqs-java` code or `.so` binaries are stored in this repository. Instead, [`liboqs-android/init-prebuilt-liboqs.sh`](liboqs-android/init-prebuilt-liboqs.sh) clones upstream `liboqs-java` and `liboqs`, compiles `liboqs.so` from C source using `liboqs/scripts/build-android.sh`, and places the generated headers, `.so` files, JNI C sources, `org.openquantumsafe` Java bindings, and `build-info.txt` into gitignored paths inside `liboqs-android/`.

Gradle automatically runs `initPrebuiltLiboqs` before `preBuild` whenever generated files are missing, or you can run the script directly:

```bash
# Compile liboqs from source for all supported Android ABIs (armeabi-v7a, arm64-v8a, x86, x86_64)
./liboqs-android/init-prebuilt-liboqs.sh

# Compile only specific ABI(s) for faster local iteration (e.g. emulator or physical device)
ABIS="arm64-v8a" ./liboqs-android/init-prebuilt-liboqs.sh
# Or pass via Gradle property:
./gradlew assembleDebug -Pabis=arm64-v8a

# Pass custom CMake flags (e.g. Classic McEliece is OFF by default in upstream liboqs since PR #2640)
EXTRA_CMAKE_FLAGS="-DOQS_ENABLE_KEM_CLASSIC_MCELIECE=ON" ./liboqs-android/init-prebuilt-liboqs.sh
```

Requirements:
- Git, Bash (Git Bash on Windows), CMake (from PATH or Android SDK `cmake/`), Ninja (optional, auto-detected), and an Android NDK (detected from `ANDROID_NDK_LATEST_HOME`, `ANDROID_NDK_HOME`, `ANDROID_NDK_ROOT`, `local.properties`, or Android SDK `ndk/` / `ndk-bundle`; downloaded automatically on Linux CI if missing).
- Supported ABIs: `armeabi-v7a`, `arm64-v8a`, `x86`, and `x86_64` (minimum Android SDK level 21).

## GitHub Actions CI & Releases

The workflow [`.github/workflows/test.yml`](.github/workflows/test.yml) compiles `liboqs` and `liboqs-java` from source for all four Android ABIs inside GitHub Actions (without committing any binaries), runs unit tests and Android emulator instrumented tests (`x86_64`, API 21), and publishes a GitHub Release containing:

- `liboqs-android-release.aar` and `liboqs-android-debug.aar` (bundling `liboqs.so` and `liboqs-jni.so` for all 4 ABIs)
- `app-debug.apk`
- `liboqs-android-natives.zip` (compiled `liboqs.so` files, `include/oqs/*` headers, and upstream `build-info.txt` commit SHAs)

Pushing to `master` updates the `latest-master` release; pushing a `v*` tag creates a versioned release.

## Important notes for Android usage

- **Thread stack size (e.g. Classic McEliece)**: Several post-quantum algorithms allocate large buffers on the C call stack, which can exceed Android's default thread stack size and cause `SIGSEGV`. Run key generation / encapsulation / signing on a `Thread` created with a larger stack size (for example `8L * 1024 * 1024` bytes), as shown in `MainActivity.java`, `KEMTest.java`, and `SignatureTest.java`.
- **Classic McEliece enablement**: Upstream `liboqs` disabled `OQS_ENABLE_KEM_CLASSIC_MCELIECE` by default in `liboqs#2640`. To include Classic McEliece parameter sets in your local build, pass `-DOQS_ENABLE_KEM_CLASSIC_MCELIECE=ON` via `-f` / `EXTRA_CMAKE_FLAGS`.
- **Native library loading**: Call `CommonAndroid.loadNativeLibrary()` before accessing `org.openquantumsafe.*` classes so `liboqs` and `liboqs-jni` are loaded in order.

## Modifying `liboqs` or `liboqs-java` C code (local development)

`init-prebuilt-liboqs.sh` clones into `liboqs-android/prebuild-liboqs/` (`liboqs` and `liboqs-java`) and **preserves existing clones** unless `--clean` / `-c` is passed:

1. Run `./liboqs-android/init-prebuilt-liboqs.sh` once to clone and compile upstream sources.
2. **Modifying `liboqs` C code**: Edit files inside `liboqs-android/prebuild-liboqs/liboqs/`, then simply Build/Run again in Android Studio (Gradle automatically detects modified C/header files under `prebuild-liboqs/liboqs/src/` and re-runs `initPrebuiltLiboqs` before `preBuild`, or you can pass `-PrebuildLiboqs`), or run `./liboqs-android/init-prebuilt-liboqs.sh` directly (`ABIS="arm64-v8a" ./liboqs-android/init-prebuilt-liboqs.sh` / `-Pabis=arm64-v8a` for fast single-ABI rebuilds). Existing clones and local edits are preserved while `liboqs.so` and `liboqs-android/jni/include/oqs` are updated.
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
