
#include "render_context.h"

RenderContext* RenderContext::instance_ = nullptr;

RenderContext::RenderContext() {

}

RenderContext::~RenderContext() {
    if (base_render_) {
        delete base_render_;
        base_render_ = nullptr;
    }
}

void RenderContext::Init(JNIEnv* env, jobject asset_manager, int type, jstring asset_path) {
    LogD("RenderContext Init type=%d", type);
    base_render_ = glesfactory::createRender(env, asset_manager, type, asset_path);
}

void RenderContext::OnSurfaceCreated() {
    glClearColor(1.0f,1.0f,1.0f, 1.0f);
    if (base_render_) {
        base_render_->OnSurfaceCreated();
    }
}

void RenderContext::OnSurfaceChanged(int width, int height) {
    glViewport(0, 0, width, height);
    if (base_render_) {
        base_render_->OnSurfaceChanged(width, height);
    }
}

void RenderContext::OnDrawFrame() {
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    if (base_render_) {
        base_render_->OnDrawFrame();
    }
}

RenderContext * RenderContext::GetInstance() {
    if (instance_ == nullptr) {
        instance_ = new RenderContext();
    }
    return instance_;
}

void RenderContext::DestroyInstance() {
    if (instance_) {
        delete instance_;
        instance_ = nullptr;
    }
}


