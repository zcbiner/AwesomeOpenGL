
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

void RenderContext::Init(AAssetManager* asset_manager, int type) {
    LogD("RenderContext Init type=%d", type);
    base_render_ = glesfactory::createRender(asset_manager, type);
}

void RenderContext::OnSurfaceCreated() {
    if (base_render_) {
        base_render_->OnSurfaceCreated();
    }
}

void RenderContext::OnSurfaceChanged(int width, int height) {
    if (base_render_) {
        base_render_->OnSurfaceChanged(width, height);
    }
}

void RenderContext::OnDrawFrame() {
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


