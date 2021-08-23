package com.zcbiner.gles.render

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import com.zcbiner.glsl.R
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 将绘制工作交给native的OpenGL。
 */
class NativeRender(context: Context, type: Int) : GLSurfaceView.Renderer {

    companion object {
        init {
            System.loadLibrary("nativegles-lib")
        }
    }

    init {
        nativeInit(context.assets, type)
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

    fun setImageData(width: Int, height: Int, bitmapData: ByteArray) {
        nativeSetBitmapData(width, height, bitmapData)
    }

    fun destroy() {
        nativeUnInit()
    }

    private external fun nativeInit(assetManager: AssetManager, type: Int)

    private external fun nativeOnSurfaceCreated()

    private external fun nativeOnSurfaceChanged(width: Int, height: Int)

    private external fun nativeOnDrawFrame()

    private external fun nativeUnInit()

    private external fun nativeSetBitmapData(width: Int, height: Int, bitmapData: ByteArray)
}