#include <jni.h>
#include <string>
#include "AndroidLog.h"
#include "Config.h"

extern "C" JNIEXPORT jstring

JNICALL
Java_org_lynxz_hellojniapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    // 导入的 Config.h 在 AS 中标红提示错误,但运行时是存在的
    LOGD("version %d %d", VERSION_MAJOR, VERSION_MINOR);

    return env->NewStringUTF(hello.c_str());
}
