#include "base_render.h"

BaseRender::BaseRender() {
	program_ = 0;
	vertex_handler_ = 0;
	frag_handler_ = 0;
	surface_width_ = 0;
	surface_height_ = 0;
}