#ifndef AWESOMEOPENGL_TEXTURE_RENDER_H
#define AWESOMEOPENGL_TEXTURE_RENDER_H


#include "../base_render.h"

/**
 * 纹理渲染。
 */
class TextureRender : public BaseRender {

public:
  TextureRender(AAssetManager* asset_manager);
  ~TextureRender();
  void OnSurfaceCreated();
  void OnSurfaceChanged(int width, int height);
  void OnDrawFrame();
  void SetBitmapData(int width, int height, uint8_t *image_data);

private:
  int texture_width;
  int texture_height;
  uint8_t *texture_data;
};


#endif //AWESOMEOPENGL_TEXTURE_RENDER_H
