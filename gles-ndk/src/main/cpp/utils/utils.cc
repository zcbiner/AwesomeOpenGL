#include "utils.h"
#include "logcat.h"

namespace glesutils {

static GLuint LoadShader(GLenum shader_type, const char *p_source) {
    GLuint shader = glCreateShader(shader_type);
    if (shader) {
        glShaderSource(shader, 1, &p_source, NULL);
        glCompileShader((shader));
        GLint compiled = 0;
        glGetShaderiv(shader, GL_COMPILE_STATUS, &compiled);
        if (!compiled) {
            GLint info_len = 0;
            glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &info_len);
            if (info_len) {
                char *buff = (char *) malloc((size_t) info_len);
                if (buff) {
                    glGetShaderInfoLog(shader, info_len, NULL, buff);
                    LogE("[LoadShader] compile shader error %d:\n%s\n", shader_type, buff);
                    free(buff);
                }
                glDeleteShader(shader);
                shader = 0;
            }
        }
    }
    return shader;
}

static GLuint CreateProgram(const char *p_vertex_shader, GLuint &vertex_shader_handler,
                            const char *p_frag_shader, GLuint &frag_shader_handler) {
    GLuint program = 0;
    // 加载顶点着色器
    vertex_shader_handler = LoadShader(GL_VERTEX_SHADER, p_vertex_shader);
    if (!vertex_shader_handler) {
        return program;
    }

    // 加载片元着色器
    frag_shader_handler = LoadShader(GL_FRAGMENT_SHADER, p_frag_shader);
    if (!frag_shader_handler) {
        return program;
    }

    // 创建程序
    program = glCreateProgram();
    // 若程序创建成功则向程序中加入顶点着色器与片元着色器
    if (program) {
        // 向程序中加入顶点着色器
        glAttachShader(program, vertex_shader_handler);
        // 向程序中加入片元着色器
        glAttachShader(program, frag_shader_handler);
        // 链接程序
        glLinkProgram(program);
        // TODO: 加入错误检测和校验。
    }
    return program;
}

static const char* LoadFromAssets(JNIEnv* env,
                             jobject assert_manager, jstring file_name) {
    AAssetManager* manager = AAssetManager_fromJava(env, assert_manager);
    jboolean is_copy;
    const char* file_name_chars = env->GetStringUTFChars(file_name, &is_copy);
    AAsset* assets = AAssetManager_open(manager, file_name_chars, AASSET_MODE_BUFFER);
    const char *data = (const char*) AAsset_getBuffer(assets);
    env->ReleaseStringUTFChars(file_name, file_name_chars);
    AAsset_close(assets);
    return data;
}
}