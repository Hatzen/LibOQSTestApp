Getting error when compiling

root@LAPTOP-xxx:/home/hatzen/liboqs-test-android# bash scripts/build-android.sh /home/hatzen/ndk/android-ndk-r21 -s 19 -a x86
Valid directory for NDK at /home/hatzen/ndk/android-ndk-r21
Compiling for ABI x86
Compiling for SDK 19
Cleaning up previous build
Building in directory build
-- The ASM compiler identification is Clang
-- Found assembler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang
-- Check for working C compiler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang
-- Check for working C compiler: /home/hatzen/ndk/android-ndk-r21/toolchains/llvm/prebuilt/linux-x86_64/bin/clang -- works
-- Detecting C compiler ABI info
-- Detecting C compiler ABI info - done
-- Detecting C compile features
-- Detecting C compile features - done
CMake Error at CMakeLists.txt:32 (message):
  Unknown or unsupported processor: i686


-- Configuring incomplete, errors occurred!
See also "/home/hatzen/liboqs-test-android/build/CMakeFiles/CMakeOutput.log".