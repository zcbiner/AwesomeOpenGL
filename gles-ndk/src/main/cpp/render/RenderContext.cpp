
#include "RenderContext.h"
#include "../content/GeometryRender.h"

RenderContext* RenderContext::render_context_ = nullptr;

RenderContext::RenderContext() {

}

RenderContext::~RenderContext() {
    if (base_render_) {
        delete base_render_;
        base_render_ = nullptr;
    }
}

void RenderContext::Init(int type) {
    switch (type) {
        case 0:
            base_render_ = new GeometryRender();
            break;
    }
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
    if (render_context_ == nullptr) {
        render_context_ = new RenderContext();
    }
    return render_context_;
}

void RenderContext::DestroyInstance() {
    if (render_context_) {
        delete render_context_;
        render_context_ = nullptr;
    }
}


