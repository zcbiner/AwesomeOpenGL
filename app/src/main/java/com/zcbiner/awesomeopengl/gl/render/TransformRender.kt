package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 用于学习怎么进行变换（scale，rotate，translate）。
 */
class TransformRender(context: Context): BaseRender(context) {

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
    }

}