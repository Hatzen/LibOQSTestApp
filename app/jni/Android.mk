JNI_DIR := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE     := oqs-jni
LOCAL_C_INCLUDES := $(JNI_DIR)/jni/
LOCAL_CFLAGS     += -Wall

LOCAL_SRC_FILES := $(JNI_DIR)/jni/handle.c $(JNI_DIR)/jni/KEMs.c  $(JNI_DIR)/jni/KeyEncapsulation.c  $(JNI_DIR)/jni/Rand.c  $(JNI_DIR)/jni/Signature.c  $(JNI_DIR)/jni/Sigs.c

include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := oqs-prebuilt
LOCAL_SRC_FILES := jniLibs/$(TARGET_ARCH_ABI)/liboqs.so
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)