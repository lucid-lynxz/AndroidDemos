
#ifndef OPENSLESDEMO_ANDROIDLOG_H
#define OPENSLESDEMO_ANDROIDLOG_H

#include <android/log.h>

#define TAG  "helloJniTag"
// 发布正式版时,请改为0
#define ENABLE_LOG 1

#if ENABLE_LOG >= 1
#define log_print_verbose(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define log_print_debug(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define log_print_info(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define log_print_warn(...) __android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__)
#define log_print_error(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#else
#define log_print_verbose(...)
#define log_print_debug(...)
#define log_print_info(...)
#define log_print_warn(...)
#define log_print_error(...)
#endif

#define LOGV(...) log_print_verbose(__VA_ARGS__)
#define LOGD(...) log_print_debug(__VA_ARGS__)
#define LOGI(...) log_print_info(__VA_ARGS__)
#define LOGW(...) log_print_warn(__VA_ARGS__)
#define LOGE(...) log_print_error(__VA_ARGS__)
#endif //OPENSLESDEMO_ANDROIDLOG_H