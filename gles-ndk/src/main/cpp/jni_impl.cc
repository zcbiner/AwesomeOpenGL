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
Java_com_zcbiner_gles_render_NativeRender_nativeSetBitmap(JNIEnv *env, jobject,
                                                          jobject bitmap) {
    AndroidBitmapInfo bitmap_info;
    int ret = AndroidBitmap_getInfo(env, bitmap, &bitmap_info);
    if (ret < 0) {
    	LogE("[nativeSetBitmap] AndroidBitmap_getInfo error");
	    return;
    }
    // 读取 bitmap 的像素内容到 native 内存
    unsigned char *bitmap_pixels;
    ret = AndroidBitmap_lockPixels(env, bitmap, reinterpret_cast<void **>(&bitmap_pixels));
    if (ret < 0) {
	    LogE("[nativeSetBitmap] AndroidBitmap_lockPixels error");
	    return;
    }
    size_t bitmap_size = bitmap_info.stride * bitmap_info.height;
    auto *new_bitmap_pixels = (unsigned char *) malloc(bitmap_size * sizeof(unsigned char));
    memcpy(new_bitmap_pixels, bitmap_pixels, bitmap_size);
	ret = AndroidBitmap_unlockPixels(env, bitmap);
    if (ret < 0) {
        LogE("[nativeSetBitmap] AndroidBitmap_unlockPixels error");
        return;
    }
	RenderContext::GetInstance()->SetBitmapData(bitmap_info.width, bitmap_info.height, new_bitmap_pixels);
}