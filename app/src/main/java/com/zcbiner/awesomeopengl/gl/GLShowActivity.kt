package com.zcbiner.awesomeopengl.gl

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zcbiner.awesomeopengl.R
import com.zcbiner.awesomeopengl.gl.render.GeometryRender
import com.zcbiner.awesomeopengl.gl.render.ImageRender
import kotlinx.android.synthetic.main.activity_gl_show.*

class GLShowActivity : AppCompatActivity() {

    companion object {
        const val KEY_RENDER = "key_render"

        const val RENDER_GEOMETRY = "geometry"
        const val RENDER_TEXTURE = "texture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gl_show)
        initGLView()
    }

    private fun initGLView() {
        glView.setEGLContextClientVersion(2)

        val renderKey = intent?.getStringExtra(KEY_RENDER)
        if (renderKey.isNullOrEmpty()) {
            return
        }

        setGLRender(renderKey)

        // 设置GLSurfaceView的渲染模式为GLSurfaceView.RENDERMODE_CONTINUOUSLY或不设置时，系统就会主动回调onDrawFrame()方法.
        // 如果设置为 RENDERMODE_WHEN_DIRTY ，手动调用requestRender()，才会渲染。
        glView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    private fun setGLRender(renderKey: String) {
        when(renderKey) {
            RENDER_GEOMETRY -> {
                glView.setRenderer(GeometryRender(this@GLShowActivity))
            }
            RENDER_TEXTURE -> {
                glView.setRenderer(ImageRender(this@GLShowActivity))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        glView.onResume()
    }

    override fun onPause() {
        super.onPause()
        glView.onPause()
    }
}