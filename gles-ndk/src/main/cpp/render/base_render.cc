#include "base_render.h"

BaseRender::BaseRender(AAssetManager* asset_manager) {
	this->asset_manager_ = asset_manager;

	program_ = 0;
	vertex_handler_ = 0;
	frag_handler_ = 0;
	surface_width_ = 0;
	surface_height_ = 0;
}

GLuint BaseRender::CreateProgram(const char *p_vertex_shader_name,
                                 const char *p_frag_shader_name) {
	const char* p_vertex_shader = ShaderUtils::LoadShaderFromAssets(asset_manager_, p_vertex_shader_name);
	const char* p_frag_shader = ShaderUtils::LoadShaderFromAssets(asset_manager_, p_frag_shader_name);
	return ShaderUtils::CreateProgram(p_vertex_shader, vertex_handler_, p_frag_shader, frag_handler_);
}

void BaseRender::OnSurfaceChanged(int width, int height) {
	this->surface_width_ = width;
	this->surface_height_ = height;
}