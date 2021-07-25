#ifndef AWESOMEOPENGL_RENDERFACTORY_H
#define AWESOMEOPENGL_RENDERFACTORY_H

#include "base_render.h"
#include "content/triangle_render.h"

namespace glesfactory{

static BaseRender* createRender(int type) {
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