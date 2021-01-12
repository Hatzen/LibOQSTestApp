# Setup linux subsystem for windows

enable with specific command in command line.
install from windows store.
access via explorer: \\wsl$\Ubuntu-18.04

# Setup env

pwd
>/home/hatzen
sudo su
apt-get update   
mkdir liboqs    
cd liboqs
git clone https://github.com/open-quantum-safe/liboqs.git   
mkdir ndk      
cd ndk
wget https://dl.google.com/android/repository/android-ndk-r21-linux-x86_64.zip 
apt-get install unzip  
unzip android-ndk-r21-linux-x86_64.zip
apt-get install -y build-essential cmake libssl-dev    
cd /home/hatzen/liboqs/liboqs/scripts     


# Checkout liboqs
## Old trials probably Outdated just checkout master
git clone --recursive -j8 -n https://github.com/open-quantum-safe/liboqs.git     
// Version of android crosscompile theoretically working. Leads to JNI errors and test errors.
// ALso tried faa7589e6f01c8d479462a45aa1cdeeb2667702d  but no chance with it.
git checkout 1b9aecc65672f86487018ee6f9786216578e4e29  
// 0.4.0 Version which cannot compile cause of aes_256 functions not found after getting fixed with 
git checkout 9ed69af2c5c8923ea2674558dd1d37f5d498ee51

# Description

The jni files are copied from liboqs-java and the class packages are adjusted only.
The CMake file is copied from liboqs and the jni lib is added only.

Java_org_openquantumsafe => Java_de_hartz_software_parannoying_helper_security_liboqs_java
org/openquantumsafe => de/hartz/software/parannoying/helper/security/liboqs/java

And changed the common.java#loadNativeLibs() to load it for only android.

# Alg support.
.CMake/alg_support.cmake
To boost compile time only enable some of the algorithms via:

option(OQS_ENABLE_KEM_KYBER "" ON)
...
option(OQS_ENABLE_KEM_NEWHOPE "" OFF)

# Failing Tests
https://github.com/open-quantum-safe/liboqs/wiki/Customizing-liboqs#oqs_build_only_lib
In android build script add:
-DOQS_BUILD_ONLY_LIB=ON    

to just skip the tests..

# ADD JNI
src/CMakeList.txt
...
include_directories(jni)
link_directories(jni oqs)

add_library(jni SHARED
jni/handle.c
jni/KEMs.c
jni/KeyEncapsulation.c
jni/Rand.c
jni/Signature.c
jni/Sigs.c) # Probably shared is needed but leads to not finding oqs

set_target_properties(
    jni
    PROPERTIES
    COMPILE_FLAGS "-w"
    #COMPILE_WARNING_DISABLED high
)
target_link_libraries(jni oqs)

set_target_properties(jni PROPERTIES PUBLIC_HEADER "jni/handle.h;jni/KEMs.h;jni/KeyEncapsulation.h;jni/Rand.h;jni/Signature.h;jni/Sigs.h")

INSTALL(TARGETS jni
        LIBRARY DESTINATION lib
        PUBLIC_HEADER DESTINATION lib
)
...

# Compiling
For compiling for Y3 run:
$  bash build-android.sh /home/hatzen/ndk/android-ndk-r21 -s 19 -a arm64-v8a

# Copy so files
copy the files from build/lib/liboqs.so to jniLibs/$ABI_COMPILED_FOR/liboqs.so

also copy the jnis from build/src/libjni.so to  jniLibs/$ABI_COMPILED_FOR/libjni.so