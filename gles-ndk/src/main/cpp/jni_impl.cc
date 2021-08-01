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
    void *bitmap_pixels;
    ret = AndroidBitmap_lockPixels(env, bitmap, &bitmap_pixels);
    if (ret < 0) {
	    LogE("[nativeSetBitmap] AndroidBitmap_lockPixels error");
	    return;
    }
    auto *src_bitmap_pixels = static_cast<uint32_t *>(bitmap_pixels);
	uint32_t width = bitmap_info.width;
	uint32_t height = bitmap_info.height;
	auto *new_bitmap_pixels = new uint32_t[width * height];
	memcpy(new_bitmap_pixels, src_bitmap_pixels, sizeof(uint32_t) * (width * height));
	AndroidBitmap_unlockPixels(env, bitmap);
	RenderContext::GetInstance()->SetBitmapData(new_bitmap_pixels);
}