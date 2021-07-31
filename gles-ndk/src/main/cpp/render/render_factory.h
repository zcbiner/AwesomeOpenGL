#ifndef AWESOMEOPENGL_RENDERFACTORY_H
#define AWESOMEOPENGL_RENDERFACTORY_H

#include "base_render.h"
#include "content/triangle_render.h"

namespace glesfactory{

static BaseRender* createRender(JNIEnv* env, jobject asset_manager, int type, jstring asset_path) {
	BaseRender *baseRender = nullptr;
	switch (type) {
		case 0:
			baseRender =  new TriangleRender();
			break;
		case 1:
			break;
		default:
			break;
	}
	return baseRender;
}

}
#endif //AWESOMEOPENGL_RENDERFACTORY_H