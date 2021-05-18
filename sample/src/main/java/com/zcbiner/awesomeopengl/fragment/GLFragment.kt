package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import com.zcbiner.awesomeopengl.gl.render.*

/**
 * 无特殊逻辑的Fragment。
 */

class TriangleFragment: BaseGLFragment() {
    override fun provideRender(context: Context): BaseRender {
        return TriangleRender(context)
    }
}

class ImageFragment: BaseGLFragment() {
    override fun provideRender(context: Context): BaseRender {
        return ImageRender(context)
    }
}

class TransformFragment: BaseGLFragment() {
    override fun provideRender(context: Context): BaseRender {
        return TransformRender(context)
    }
}

class ColorFragment: BaseGLFragment() {
    override fun provideRender(context: Context): BaseRender {
        return ColorRender(context)
    }
}

class CubeFragment: BaseGLFragment() {
    override fun provideRender(context: Context): BaseRender {
        return CubeRender(context)
    }
}