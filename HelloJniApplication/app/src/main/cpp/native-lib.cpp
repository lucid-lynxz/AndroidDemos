#include <jni.h>
#include <string>
#include "AndroidLog.h"
#include "Config.h"

//#if ENABLE_MY_MATH_LIB == 1
#ifdef use_my_math_lib

#include "../MyMath/MySqrt.h"

#endif

void JNI_ThrowByName(JNIEnv *env, const char *name, const char *msg) {
    // 查找异常类
    jclass cls = env->FindClass(name);
    /* 如果这个异常类没有找到，VM会抛出一个NowClassDefFoundError异常 */
    if (cls != NULL) {
        env->ThrowNew(cls, msg); // 抛出指定名字的异常
    }
    /* 释放局部引用 */
    env->DeleteLocalRef(cls);
}

const char *input_invalid_exception = "input fail";
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
    try {
        mySqrt(50);
        // 若未 try...catch,则程序会崩溃报错: Fatal signal 6 (SIGABRT)
        mySqrt(-5);
    } catch (MyException e) {
        LOGD("error occurs as %d %s ", e.errNo, e.errMsg.c_str());

        JNI_ThrowByName(env, "java/lang/Exception", e.errMsg.c_str());
        return nullptr;
//        env->ExceptionDescribe(); // 打印异常信息
//        env->ExceptionClear(); // 清空异常堆栈
    }
#endif

    // 不能在有异常时调用 NewStringUTF , 否则会报错: JNI NewStringUTF called with pending exception
    // 需要  ExceptionClear() 或者直接在发生异常时调用return
    return env->NewStringUTF(hello.c_str());
}


