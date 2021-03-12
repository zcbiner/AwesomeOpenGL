//
// 工具类。用于处理glsl、program。
//

#ifndef AWESOMEOPENGL_GLUTILS_H
#define AWESOMEOPENGL_GLUTILS_H

#include <GLES3/gl3.h>
#include <android/log.h>
#include <string>

class GLUtils {
public:
    static GLuint LoadShader(GLenum shaderType, const char *pSource);
    static GLuint CreateProgram(const char *pVertexShaderSource, const char *pFragShaderSource,
            GLuint &vertexShaderHandle, GLuint &fragShaderHandle);


    static void setBool(GLuint programId, const std::string &name, bool value) {
        glUniform1i(glGetUniformLocation(programId, name.c_str()), (int) value);
    }

    static void setInt(GLuint programId, const std::string &name, int value) {
        glUniform1i(glGetUniformLocation(programId, name.c_str()), value);
    }

    static void setFloat(GLuint programId, const std::string &name, float value) {
        glUniform1f(glGetUniformLocation(programId, name.c_str()), value);
    }
};


#endif //AWESOMEOPENGL_GLUTILS_H
