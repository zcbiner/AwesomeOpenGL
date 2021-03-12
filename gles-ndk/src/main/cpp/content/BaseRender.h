#ifndef AWESOMEOPENGL_BASERENDER_H
#define AWESOMEOPENGL_BASERENDER_H

#include "stdint.h"
#include <GLES3/gl3.h>

class BaseRender {
public:
    BaseRender() {

    }

    virtual void OnSurfaceCreated(){};
    virtual void OnSurfaceChanged(int width, int height){};
    virtual void OnDrawFrame(){};
};

#endif //AWESOMEOPENGL_BASERENDER_H
