#include "triangle_render.h"

TriangleRender::TriangleRender(AAssetManager* asset_manager)
		: BaseRender(asset_manager) {
	LogD("[TriangleRender] init.");
}

void TriangleRender::OnSurfaceCreated() {
	const char* p_vertex_shader_name = "triangle_vertex.glsl";
	const char* p_frag_shader_name = "triangle_frag.glsl";
	program_ = CreateProgram(p_vertex_shader_name, p_frag_shader_name);
	if (program_ > 0) {
		glUseProgram(program_);
		pos_handler_ = glGetAttribLocation(program_, "vPosition");
		color_handler_ = glGetUniformLocation(program_, "vColor");
	}
}

void TriangleRender::OnDrawFrame() {
	GLfloat vertices[] = {
			0.0f,  0.5f, 0.0f,
			-0.5f, -0.5f, 0.0f,
			0.5f, -0.5f, 0.0f,
	};
	glVertexAttribPointer(0, kVertexCount, GL_FLOAT, GL_FALSE, 0, vertices);
	glEnableVertexAttribArray(0);
	glDrawArrays(GL_TRIANGLES, 0, kVertexCount);
}

void TriangleRender::OnSurfaceChanged(int width, int height) {
	this->surface_width_ = width;
	this->surface_height_ = height;
}
