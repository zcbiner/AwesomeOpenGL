//
// 自定义渲染上下文。
// 1.用于与JNI交互
// 2.管理渲染对象
//
#include <GLES3/gl3.h>
#include "stdint.h"
#include "../content/BaseRender.h"

#ifndef AWESOMEOPENGL_RENDERCONTEXT_H
#define AWESOMEOPENGL_RENDERCONTEXT_H

class RenderContext {
    RenderContext();
    ~RenderContext();
public:

    void Init(int type);
    void OnSurfaceCreated();
    void OnSurfaceChanged(int width, int height);
    void OnDrawFrame();

    static RenderContext* GetInstance();
    static void DestroyInstance();

private:
    static RenderContext *render_context_;
    BaseRender *base_render_;
};

#endif //AWESOMEOPENGL_RENDERCONTEXT_H
