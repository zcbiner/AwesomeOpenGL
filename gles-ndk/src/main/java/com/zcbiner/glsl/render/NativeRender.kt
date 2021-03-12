package com.zcbiner.glsl.render

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 将绘制工作交给native的OpenGL。
 */
class NativeRender(type: Int) : GLSurfaceView.Renderer {

    companion object {
        init {
            System.loadLibrary("nativegles-lib")
        }
    }

    init {
        nativeInit(type)
    }

    override fun onDrawFrame(gl: GL10?) {
        nativeOnDrawFrame()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        nativeOnSurfaceChanged(width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        nativeOnSurfaceCreated()
    }

    external fun nativeInit(type: Int)

    external fun nativeOnSurfaceCreated()

    external fun nativeOnSurfaceChanged(width: Int, height: Int)

    external fun nativeOnDrawFrame()

    external fun nativeUnInit()
}