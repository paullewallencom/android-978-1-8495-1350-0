LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

# We only want this apk build for tests.
LOCAL_MODULE_TAGS := tests

LOCAL_JAVA_LIBRARIES := android.test.runner

LOCAL_STATIC_JAVA_LIBRARIES := easymock hamcrest-core \
	hamcrest-integration hamcrest-library

# Include all test java files.
LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := TemperatureConverterTest

LOCAL_INSTRUMENTATION_FOR := TemperatureConverter

LOCAL_SDK_VERSION := current

include $(BUILD_PACKAGE)

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := \
	easymock:libs/easymock-2.5.2.jar \
	hamcrest-core:libs/hamcrest-core-1.2-android.jar \
	hamcrest-integration:libs/hamcrest-integration-1.2-android.jar \
	hamcrest-library:libs/hamcrest-library-1.2-android.jar
include $(BUILD_MULTI_PREBUILT)
