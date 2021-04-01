# LibOQSTestApp
  
![KemTest on Android x86_64](https://github.com/Hatzen/LibOQSTestApp/workflows/Test%20Liboqs%20on%20Android/badge.svg?branch=master) 

This app shows how to use liboqs on android devices. Usually you would use the openssl implementation but for end to end encryption using it locally may be useful.  

Currently there constant copies of https://github.com/open-quantum-safe/liboqs and https://github.com/open-quantum-safe/liboqs-java are used as android needs further configurations to build them for the correct abi.

The prebuild liboqs.so files (https://github.com/Hatzen/LibOQSTestApp/tree/master/app/jni/jniLibs) are generated with https://github.com/open-quantum-safe/liboqs/blob/main/scripts/build-android.sh manually.  
There seems to be an issue (or further configuration) with compiling for 32 Bit abis like x86 and arm-7. Because of this the local emulator might not compile as no .so file is provided.

The jni files (https://github.com/Hatzen/LibOQSTestApp/tree/master/app/jni/jni) are slightly modified (package name changes and a minor fix) to compile successfully. 

The example app is currently just a mockup without functionality. 

## TODOs
 - [ ] Setup CI to use newest versions of jni and liboqs
 - [ ] Implement example app mock up
