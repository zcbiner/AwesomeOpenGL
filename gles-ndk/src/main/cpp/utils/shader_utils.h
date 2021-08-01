#ifndef AWESOMEOPENGL_SHADER_UTILS_H
#define AWESOMEOPENGL_SHADER_UTILS_H

#include <GLES3/gl3.h>
#include <stdlib.h>
#include <jni.h>
#include <android/asset_manager_jni.h>
#include <android/asset_manager.h>
#include "logcat.h"

class ShaderUtils {

public:
  static GLuint CreateProgram(const char *p_vertex_shader, GLuint &vertex_shader_handler,
                              const char *p_frag_shader, GLuint &frag_shader_handler);

  static const char* LoadShaderFromAssets(AAssetManager* asset_manager, const char* file_name);

private:
  static GLuint LoadShader(GLenum shader_type, const char *p_source);

};

#endif //AWESOMEOPENGL_SHADER_UTILS_H
