# LibOQSTestApp
  
![KemTest on Android x86_64](https://github.com/Hatzen/LibOQSTestApp/workflows/Test%20Liboqs%20on%20Android/badge.svg?branch=master) 

This is an unofficial test app for https://github.com/open-quantum-safe/liboqs .  
This app shows how to use liboqs on android devices. Usually you would use the openssl implementation but for end to end encryption using it locally may be useful.  

Currently constant copies of https://github.com/open-quantum-safe/liboqs and https://github.com/open-quantum-safe/liboqs-java are used as android needs further configurations to build them for the correct abi.

The prebuild liboqs.so files (https://github.com/Hatzen/LibOQSTestApp/tree/master/app/jni/jniLibs) are generated with https://github.com/open-quantum-safe/liboqs/blob/main/scripts/build-android.sh manually.  
There seems to be an issue (or further configuration) with compiling for 32 Bit abis like x86 and arm-7 (https://github.com/Hatzen/LibOQSTestApp/issues/2 and https://github.com/Hatzen/LibOQSTestApp/issues/3). Because of this the local emulator (x86) might not compile as no .so file is provided.

The jni files (https://github.com/Hatzen/LibOQSTestApp/tree/master/app/jni/jni) are slightly modified (package name changes and a minor fix) to compile successfully.  
And the way of loading the liboqs.so files had to be changed: https://github.com/Hatzen/LibOQSTestApp/blob/master/liboqs-android/src/main/java/com/example/liboqs/Common.java#L31
  
The project is split into 
  
1)  the module which wraps the JNI Interface to use liboqs on android (https://github.com/Hatzen/LibOQSTestApp/tree/master/liboqs-android).  
The package name "com.example.liboqs.*" will change in future and may produce a breaking change.  

2) and an example app (https://github.com/Hatzen/LibOQSTestApp/tree/master/app) showing the usage with a fictional example
<img src="https://user-images.githubusercontent.com/21283655/114078514-53566d00-98a9-11eb-919e-b587c62e41bd.png" height="300">  
  
## TODOs
 - [ ] Setup CI to use newest versions of jni and liboqs
