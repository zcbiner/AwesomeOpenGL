#include <jni.h>
#include "render_context.h"

#ifdef __cplusplus
extern "C" {
#endif

extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeInit(JNIEnv
* env,
jobject thiz, jint type
) {
    RenderContext::GetInstance()->Init(type);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeOnSurfaceCreated(JNIEnv
* env,
jobject thiz
) {
    RenderContext::GetInstance()->OnSurfaceCreated();
}


extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeOnSurfaceChanged(JNIEnv
* env,
jobject thiz, jint
width,
jint height
) {
    RenderContext::GetInstance()->OnSurfaceChanged(width, height);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeOnDrawFrame(JNIEnv
* env,
jobject thiz
) {
    RenderContext::GetInstance()->OnDrawFrame();
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeUnInit(JNIEnv
* env,
jobject thiz
) {
    RenderContext::DestroyInstance();
}
}