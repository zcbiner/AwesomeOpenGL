#include <GLES3/gl3.h>
#include "texture_render.h"


TextureRender::TextureRender(AAssetManager *asset_manager): BaseRender(asset_manager) {
    this->texture_width = 0;
    this->texture_height = 0;
    this->texture_data = nullptr;
}

TextureRender::~TextureRender() {
    free(this->texture_data);
}

void TextureRender::OnSurfaceCreated() {
    glClearColor(1.0f,1.0f,1.0f, 1.0f);

    const char* p_vertex_shader_name = "image.vert";
    const char* p_frag_shader_name = "image.frag";
    program_ = CreateProgram(p_vertex_shader_name, p_frag_shader_name);
    if (program_ > 0) {
        glUseProgram(program_);
    }

    GLfloat vertices_coord[] = {
           -1.0f,  1.0f, 0.0f,
           -1.0f, -1.0f, 0.0f,
           1.0f, -1.0f, 0.0f,
           1.0f,  1.0f, 0.0f,
    };
    GLfloat textures_coord[] = {
            0.0f,  0.0f,
            0.0f,  1.0f,
            1.0f,  1.0f,
            1.0f,  0.0f
    };
    // Load the vertex position
    glVertexAttribPointer (0, 3, GL_FLOAT,
                           GL_FALSE, 3 * sizeof (GLfloat), vertices_coord);
    // Load the texture coordinate
    glVertexAttribPointer (1, 2, GL_FLOAT,
                           GL_FALSE, 2 * sizeof (GLfloat), textures_coord);
    glEnableVertexAttribArray (0);
    glEnableVertexAttribArray (1);

    GLuint texture_id = 0;
    glGenTextures(1, &texture_id);
    glBindTexture(GL_TEXTURE_2D, texture_id);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture_width, texture_height, 0, GL_RGBA, GL_UNSIGNED_BYTE, texture_data);
    GLint u_texture = glGetUniformLocation(program_, "u_texture");
    glUniform1f(u_texture, 0);
}

void TextureRender::OnDrawFrame() {
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    GLushort indices[] = { 0, 1, 2, 0, 2, 3 };
    glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, indices);
}

void TextureRender::OnSurfaceChanged(int width, int height) {
    glViewport(0, 0, width, height);
}

void TextureRender::SetBitmapData(int width, int height, uint8_t *image_data) {
    LogD("bugFix width=%d, height=%d", width, height);
    this->texture_width = width;
    this->texture_height = height;
    this->texture_data = image_data;
}