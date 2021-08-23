#include <jni.h>
#include <android/bitmap.h>
#include "logcat.h"
#include "render_context.h"

#ifdef __cplusplus
extern "C" {
#endif

extern "C"
JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeInit(JNIEnv* env,
jobject thiz,jobject asset_manager,
jint type) {
    AAssetManager* manager = AAssetManager_fromJava(env, asset_manager);
    RenderContext::GetInstance()->Init(manager, type);
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
}extern "C"

JNIEXPORT void JNICALL
Java_com_zcbiner_gles_render_NativeRender_nativeSetBitmapData(JNIEnv *env, jobject,
        jint width, jint height, jbyteArray bitmapData) {
    int length = env->GetArrayLength(bitmapData);
    auto* buffer = new uint8_t[length];
    env->GetByteArrayRegion(bitmapData, 0, length, reinterpret_cast<jbyte*>(buffer));
    RenderContext::GetInstance()->SetBitmapData(width, height, buffer);
    env->DeleteLocalRef(bitmapData);
}