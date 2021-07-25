//
// Created by zhong on 2021/7/25.
//

#ifndef AWESOMEOPENGL_LOGCAT_H
#define AWESOMEOPENGL_LOGCAT_H

#include<android/log.h>

#define  LOG_TAG "AwesomeOpenGL"

#define  LogE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define  LogV(...)  __android_log_print(ANDROID_LOG_VERBOSE,LOG_TAG,__VA_ARGS__)
#define  LogD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define  LogI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)

#endif //AWESOMEOPENGL_LOGCAT_H
