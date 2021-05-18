package com.zcbiner.awesomeopengl.gl.render

import android.content.Context

/**
 * 图片滤镜相关Render。
 */
class GrayFilterRender(context: Context): BaseImageFilterRender(context) {
    override fun getFragShader(): String {
        return "imagefilter/frag_gray.glsl"
    }
}

class GaussFilterRender(context: Context): BaseImageFilterRender(context) {
    override fun getFragShader(): String {
        return "imagefilter/frag_gauss.glsl"
    }
}

class MosaicFilterRender(context: Context): BaseImageFilterRender(context) {
    override fun getFragShader(): String {
        return "imagefilter/frag_mosaic.glsl"
    }
}