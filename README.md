# LibOQSTestApp

Repository to test https://github.com/open-quantum-safe/liboqs with https://github.com/open-quantum-safe/liboqs-java for a android app only build.  
The code of both repositories are copied to specific places and changed a bit as it is mandatory by the android build script to support different abis.  
Currently the build is not working which relates to many different issues and my absent of knowledge to combine them :)  

Relevant c files which needs to be build manually:  
https://github.com/Hatzen/LibOQSTestApp/tree/master/app/src/main/c

Relevant java files for testing jni:  
https://github.com/Hatzen/LibOQSTestApp/blob/master/app/src/androidTest/java/com/example/liboqstestapp/KEMTest.java  
https://github.com/Hatzen/LibOQSTestApp/tree/master/app/src/main/java/com/example/liboqstestapp/liboqs
