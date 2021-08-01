//
// 自定义渲染上下文。
// 1.用于与JNI交互
// 2.管理渲染对象
//
#ifndef AWESOMEOPENGL_RENDERCONTEXT_H
#define AWESOMEOPENGL_RENDERCONTEXT_H

#include <stdint.h>
#include "base_render.h"
#include "render_factory.h"
#include "../utils/logcat.h"

class RenderContext {
    RenderContext();
    ~RenderContext();
public:

    void Init(AAssetManager* asset_manager, int type);
    void OnSurfaceCreated();
    void OnSurfaceChanged(int width, int height);
    void OnDrawFrame();

    static RenderContext* GetInstance();
    static void DestroyInstance();

private:
    static RenderContext *instance_;
    BaseRender *base_render_;
};

#endif //AWESOMEOPENGL_RENDERCONTEXT_H
