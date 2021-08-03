#include <GLES3/gl3.h>
#include "texture_render.h"


TextureRender::TextureRender(AAssetManager *asset_manager): BaseRender(asset_manager) {
    this->texture_width = 0;
    this->texture_height = 0;
    this->texture_data = nullptr;
}

void TextureRender::OnSurfaceCreated() {
    glClearColor(1.0f,1.0f,1.0f, 1.0f);

    const char* p_vertex_shader_name = "image.vert";
    const char* p_frag_shader_name = "image.frag";
    program_ = CreateProgram(p_vertex_shader_name, p_frag_shader_name);
    if (program_ > 0) {
        glUseProgram(program_);
    }

    GLint a_pos = glGetAttribLocation(program_, "a_position");
    glEnableVertexAttribArray(a_pos);
    GLfloat vertices_coord[] = {
            -1.0f, -1.0f, -1.0f,
            1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, 1.0f, -1.0f
    };
    glVertexAttribPointer(a_pos, 2, GL_FLOAT, false, 0, vertices_coord);

    GLint texture_coordinate = glGetAttribLocation(program_, "a_textureCoordinate");
    glEnableVertexAttribArray(texture_coordinate);
    GLfloat textures_coord[] = {
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f
    };
    glVertexAttribPointer(texture_coordinate, 2, GL_FLOAT, false, 0, textures_coord);

    GLuint texture_id = 0;
    glGenTextures(1, &texture_id);
    glBindTexture(GL_TEXTURE_2D, texture_id);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture_width, texture_height, 0, GL_RGBA, GL_UNSIGNED_BYTE, texture_data);
    GLint u_texture = glGetAttribLocation(program_, "u_texture");
    glUniform1f(u_texture, 0);
}

void TextureRender::OnDrawFrame() {
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    glDrawArrays(GL_TRIANGLES, 0, 6);
}

void TextureRender::OnSurfaceChanged(int width, int height) {
    glViewport(0, 0, width, height);
}

void TextureRender::SetBitmapData(uint32_t width, uint32_t height, void *bitmap_pixels) {
    this->texture_width = width;
    this->texture_height = height;
    this->texture_data = bitmap_pixels;
}