#ifndef AWESOMEOPENGL_TRIANGLE_RENDER_H
#define AWESOMEOPENGL_TRIANGLE_RENDER_H

#include "../base_render.h"

class TriangleRender : public BaseRender {
public:
  const int kCoordsPerVertex = 3;
  const float kTriangleCoords[9] = {
  		0.0f, 0.8f, 0.0f,
  		-0.5f, 0.2f, 0.0f,
  		0.5f, 0.2f, 0.0f
  };

  TriangleRender();
  void OnSurfaceCreated();
  void OnSurfaceChanged(int width, int height);
  void OnDrawFrame();

private:
  GLint pos_handler_;
  GLint color_handler_;
};


#endif //AWESOMEOPENGL_TRIANGLE_RENDER_H
