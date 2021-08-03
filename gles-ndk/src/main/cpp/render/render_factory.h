#ifndef AWESOMEOPENGL_RENDERFACTORY_H
#define AWESOMEOPENGL_RENDERFACTORY_H

#include "base_render.h"
#include "triangle_render.h"
#include "texture_render.h"

namespace glesfactory{

static BaseRender* createRender(AAssetManager* asset_manager, int type) {
	BaseRender *baseRender = nullptr;
	switch (type) {
		case 0:
			baseRender = new TriangleRender(asset_manager);
			break;
		case 1:
			baseRender = new TextureRender(asset_manager);
			break;
		default:
			break;
	}
	return baseRender;
}

}
#endif //AWESOMEOPENGL_RENDERFACTORY_H