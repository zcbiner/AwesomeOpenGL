#ifndef AWESOMEOPENGL_TEXTURE_RENDER_H
#define AWESOMEOPENGL_TEXTURE_RENDER_H


#include "../base_render.h"

/**
 * 纹理渲染。
 */
class TextureRender : public BaseRender {

public:
  TextureRender(AAssetManager* asset_manager);
  void OnSurfaceCreated();
  void OnSurfaceChanged(int width, int height);
  void OnDrawFrame();
  void SetBitmapData(uint32_t width, uint32_t height, void *bitmap_pixels);

private:
  uint32_t texture_width;
  uint32_t texture_height;
  void *texture_data;

};


#endif //AWESOMEOPENGL_TEXTURE_RENDER_H
