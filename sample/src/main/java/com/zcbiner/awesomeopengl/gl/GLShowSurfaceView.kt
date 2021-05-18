package com.zcbiner.awesomeopengl.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import com.zcbiner.awesomeopengl.gl.render.BaseRender

class GLShowSurfaceView: GLSurfaceView {

    private var baseRender: BaseRender? = null

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    override fun setRenderer(renderer: Renderer?) {
        super.setRenderer(renderer)
        if (renderer is BaseRender) {
            baseRender = renderer
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (baseRender?.onTouchEvent(event) == true) {
            return true
        }
        return super.onTouchEvent(event)
    }

}