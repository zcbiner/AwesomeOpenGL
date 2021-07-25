#include "triangle_render.h"

TriangleRender::TriangleRender() {
	LogD("[TriangleRender] init.");
}

void TriangleRender::OnSurfaceCreated() {
	const char vShaderStr[] =
			"#version 300 es                          \n"
			"layout(location = 0) in vec4 vPosition;  \n"
			"void main()                              \n"
			"{                                        \n"
			"   gl_Position = vPosition;              \n"
			"}                                        \n";

	const char fShaderStr[] =
			"#version 300 es                              \n"
			"precision mediump float;                     \n"
			"out vec4 fragColor;                          \n"
			"void main()                                  \n"
			"{                                            \n"
			"   fragColor = vec4 ( 1.0, 0.0, 0.0, 1.0 );  \n"
			"}                                            \n";

	program_ = glesutils::CreateProgram(vShaderStr, vertex_handler_, fShaderStr, frag_handler_);
	if (program_ > 0) {
		glUseProgram(program_);
		pos_handler_ = glGetAttribLocation(program_, "vPosition");
		color_handler_ = glGetUniformLocation(program_, "vColor");
	}
}

void TriangleRender::OnDrawFrame() {
	GLfloat vVertices[] = {
			0.0f,  0.5f, 0.0f,
			-0.5f, -0.5f, 0.0f,
			0.5f, -0.5f, 0.0f,
	};
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, vVertices);
	glEnableVertexAttribArray(0);
	glDrawArrays(GL_TRIANGLES, 0, 3);
}

void TriangleRender::OnSurfaceChanged(int width, int height) {

}
