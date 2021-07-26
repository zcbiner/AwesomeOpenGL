#ifndef AWESOMEOPENGL_UTILS_H
#define AWESOMEOPENGL_UTILS_H

#include <GLES3/gl3.h>
#include <stdlib.h>
#include <jni.h>
#include <android/asset_manager_jni.h>
#include <android/asset_manager.h>
#include "logcat.h"

namespace glesutils {

/**
 * 加载着色器。
 */
static GLuint LoadShader(GLenum shader_type, const char *p_source);

static GLuint CreateProgram(const char *p_vertex_shader, GLuint &vertex_shader_handler,
                            const char *p_frag_shader, GLuint &frag_shader_handler);

static const char* LoadFromAssets(JNIEnv* env,
                                   jobject assert_manager, jstring file_name);
}

#endif //AWESOMEOPENGL_UTILS_H
