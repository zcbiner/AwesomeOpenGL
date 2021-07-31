#include "base_render.h"

BaseRender::BaseRender(JNIEnv* env, jobject asset_manager, jstring asset_path) {
	this->jni_env_ = env;
	this->asset_manager_ = asset_manager;
	this->asset_path_ = asset_path;

	program_ = 0;
	vertex_handler_ = 0;
	frag_handler_ = 0;
	surface_width_ = 0;
	surface_height_ = 0;
}

const char* BaseRender::LoadVertexShaderFromAssets() {
	return LoadShaderFromAssets()
}

const char* BaseRender::LoadFragShaderFromAssets() {

}

const char* BaseRender::LoadShaderFromAssets() {
	return glesutils::LoadFromAssets(jni_env_, asset_manager_, asset_path_);
}