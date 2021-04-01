JNI_DIR := $(call my-dir)

# Analogous to https://github.com/android/ndk-samples/blob/master/other-builds/ndkbuild/hello-libs/app/Android.mk

# Prebuild dirs.
include $(CLEAR_VARS)
LOCAL_MODULE := oqs-prebuilt
LOCAL_SRC_FILES := jniLibs/$(TARGET_ARCH_ABI)/liboqs.so
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

# Create jni wrapper.
include $(CLEAR_VARS)
LOCAL_MODULE     := oqs-jni
LOCAL_C_INCLUDES := $(JNI_DIR)/jni/
LOCAL_CFLAGS     += -Wall
LOCAL_SRC_FILES := $(JNI_DIR)/jni/handle.c $(JNI_DIR)/jni/KEMs.c  $(JNI_DIR)/jni/KeyEncapsulation.c  $(JNI_DIR)/jni/Rand.c  $(JNI_DIR)/jni/Signature.c  $(JNI_DIR)/jni/Sigs.c
include $(BUILD_SHARED_LIBRARY)

