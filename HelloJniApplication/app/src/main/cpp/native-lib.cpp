#include <jni.h>
#include <string>
#include "config.h"
#include "AndroidLog.h"

extern "C" JNIEXPORT jstring

JNICALL
Java_org_lynxz_hellojniapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    LOGD("version %d %d", VERSION_MAJOR, VERSION_MINOR);
    return env->NewStringUTF(hello.c_str());
}
