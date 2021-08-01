#ifndef AWESOMEOPENGL_TRIANGLE_RENDER_H
#define AWESOMEOPENGL_TRIANGLE_RENDER_H

#include <string>
#include "../base_render.h"
#include "shader_utils.h"

class TriangleRender : public BaseRender {
public:
  TriangleRender(AAssetManager* asset_manager);

  void OnSurfaceCreated();
  void OnSurfaceChanged(int width, int height);
  void OnDrawFrame();

private:
  const GLsizei kVertexCount = 3;
  GLint pos_handler_;
  GLint color_handler_;
};


#endif //AWESOMEOPENGL_TRIANGLE_RENDER_H
