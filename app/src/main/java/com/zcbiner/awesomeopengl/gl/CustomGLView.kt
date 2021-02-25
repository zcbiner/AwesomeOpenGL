package com.zcbiner.awesomeopengl.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class CustomGLView(context: Context, attrs: AttributeSet? = null) : GLSurfaceView(context, attrs) {

    private val render: CustomGLRender

    init {
        setEGLContextClientVersion(2);
        render = CustomGLRender()
        setRenderer(render)
        renderMode = RENDERMODE_WHEN_DIRTY
    }

}