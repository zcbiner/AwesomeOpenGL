//
// 绘制简单的几何图形
//
#include "BaseRender.h"

#ifndef AWESOMEOPENGL_GEOMETRYRENDER_H
#define AWESOMEOPENGL_GEOMETRYRENDER_H

class GeometryRender: public BaseRender {

    virtual void OnSurfaceCreated();
    virtual void OnSurfaceChanged(int width, int height);
    virtual void OnDrawFrame();

};

#endif //AWESOMEOPENGL_GEOMETRYRENDER_H
