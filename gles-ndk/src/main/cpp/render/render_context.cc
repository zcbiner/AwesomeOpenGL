
#include "render_context.h"
#include "texture_render.h"

RenderContext* RenderContext::instance_ = nullptr;

RenderContext::RenderContext() {
    LogD("[RenderContext] init");
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

void RenderContext::SetBitmapData(int width, int height, uint8_t *image_data) {
    if (base_render_) {
        base_render_->SetBitmapData(width, height, image_data);
    }
}

RenderContext * RenderContext::GetInstance() {
    if (instance_ == nullptr) {
        LogD("[GetInstance] new RenderContext()");
        instance_ = new RenderContext();
    }
    return instance_;
}

void RenderContext::DestroyInstance() {
    if (instance_) {
        LogD("[DestroyInstance] instance_ = nullptr");
        delete instance_;
        instance_ = nullptr;
    }
}


