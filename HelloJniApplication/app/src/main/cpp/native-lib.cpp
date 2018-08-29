#include <jni.h>
#include <string>
#include "AndroidLog.h"
#include "Config.h"

//#if ENABLE_MY_MATH_LIB == 1
#ifdef use_my_math_lib

#include "../MyMath/MySqrt.h"

#endif
extern "C" JNIEXPORT jstring

JNICALL
Java_org_lynxz_hellojniapplication_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    // 导入的 Config.h 在 AS 中标红提示错误,但运行时是存在的
    LOGD("jni version %d %d", VERSION_MAJOR, VERSION_MINOR);

//#if ENABLE_MY_MATH_LIB == 1
#ifdef use_my_math_lib
    mySqrt(50);
#endif
    return env->NewStringUTF(hello.c_str());
}
