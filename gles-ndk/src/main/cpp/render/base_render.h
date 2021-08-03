#ifndef AWESOMEOPENGL_BASE_RENDER_H
#define AWESOMEOPENGL_BASE_RENDER_H

#include <jni.h>
#include "shader_utils.h"

class BaseRender {
public:
  BaseRender(AAssetManager* asset_manager);
  virtual void OnSurfaceCreated(){};
  virtual void OnSurfaceChanged(int width, int height){};
  virtual void OnDrawFrame(){};
  virtual void SetBitmapData(uint32_t width, uint32_t height, void *bitmap_pixels){};

protected:
  GLuint program_;
  GLuint vertex_handler_;
  GLuint frag_handler_;
  int surface_width_;
  int surface_height_;
  GLuint CreateProgram(const char* p_vertex_shader_name, const char* p_frag_shader_name);

private:
  AAssetManager* asset_manager_;
};
#endif //AWESOMEOPENGL_BASE_RENDER_H
