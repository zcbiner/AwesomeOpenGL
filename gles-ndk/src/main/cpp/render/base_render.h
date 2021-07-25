#ifndef AWESOMEOPENGL_BASE_RENDER_H
#define AWESOMEOPENGL_BASE_RENDER_H

#include "../utils/utils.h"

class BaseRender {
public:
  BaseRender();
  virtual void OnSurfaceCreated(){};
  virtual void OnSurfaceChanged(int width, int height){};
  virtual void OnDrawFrame(){};

protected:
  GLuint program_;
  GLuint vertex_handler_;
  GLuint frag_handler_;
  int surface_width_;
  int surface_height_;

};
#endif //AWESOMEOPENGL_BASE_RENDER_H
