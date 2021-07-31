#ifndef AWESOMEOPENGL_BASE_RENDER_H
#define AWESOMEOPENGL_BASE_RENDER_H

#include <jni.h>
#include "../utils/utils.h"

class BaseRender {
public:
  BaseRender(JNIEnv* env, jobject asset_manager, jstring asset_path);
  virtual void OnSurfaceCreated(){};
  virtual void OnSurfaceChanged(int width, int height){};
  virtual void OnDrawFrame(){};

protected:
  GLuint program_;
  GLuint vertex_handler_;
  GLuint frag_handler_;
  int surface_width_;
  int surface_height_;

  const char* LoadVertexShaderFromAssets();
  const char* LoadFragShaderFromAssets();

private:
  JNIEnv* jni_env_;
  jobject asset_manager_;
  jstring asset_path_;

  const char* LoadShaderFromAssets();
};
#endif //AWESOMEOPENGL_BASE_RENDER_H
