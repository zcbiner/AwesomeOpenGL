package com.zcbiner.awesomeopengl.gl

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zcbiner.awesomeopengl.R
import com.zcbiner.awesomeopengl.gl.render.BaseRender
import com.zcbiner.awesomeopengl.gl.util.RenderConfig
import kotlinx.android.synthetic.main.activity_gl_show.*

class GLShowActivity : AppCompatActivity() {

    companion object {
        const val TAG = "GLShowActivity"
        const val KEY_POS = "key_pos"
    }

    private var renderPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gl_show)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initParams()
    }

    private fun initParams() {
        renderPos = intent?.getIntExtra(KEY_POS, -1) ?: -1
        if (renderPos < 0 || RenderConfig.RENDER_CONFIG.size <= renderPos) {
            Toast.makeText(this@GLShowActivity, "暂未实现，敬请期待！",
                Toast.LENGTH_SHORT).show()
            Log.e(TAG, "renderPos is illegal, renderPos=$renderPos")
            finish()
            return
        }
        // 设置标题
        supportActionBar?.title = RenderConfig.TITLE_CONFIG[renderPos]

        initGLView()
    }

    private fun initGLView() {
        val render = createGLRender()
        glView.setEGLContextClientVersion(2)
        glView.setRenderer(render)

        // 设置GLSurfaceView的渲染模式为GLSurfaceView.RENDERMODE_CONTINUOUSLY或不设置时，系统就会主动回调onDrawFrame()方法.
        // 如果设置为 RENDERMODE_WHEN_DIRTY ，手动调用requestRender()，才会渲染。
        glView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    private fun createGLRender(): BaseRender? {
        var baseRender: BaseRender? = null
        val clazz = RenderConfig.RENDER_CONFIG[renderPos]
        clazz.constructors.forEach {
            if (it.parameters.size == 1) {
                baseRender = it.call(this@GLShowActivity)
            }
        }
        return baseRender
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
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