package com.zcbiner.awesomeopengl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zcbiner.awesomeopengl.fragment.BaseGLFragment
import com.zcbiner.awesomeopengl.gl.util.RenderConfig

/**
 * 用于加载相关的BaseFragment。
 */
class GLFragmentActivity: AppCompatActivity() {

    companion object {
        const val KEY_POS = "key_pos"

        fun start(context: Context, pos: Int) {
            val intent = Intent(context, GLFragmentActivity::class.java)
            intent.putExtra(KEY_POS, pos)
            context.startActivity(intent)
        }
    }

    private var renderPos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gl_fragment)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initParams()
    }

    private fun initParams() {
        renderPos = intent?.getIntExtra(KEY_POS, -1) ?: -1
        if (renderPos < 0 || RenderConfig.FRAGMENT_CONFIG.size <= renderPos) {
            Toast.makeText(this@GLFragmentActivity, "暂未实现，敬请期待！",
                Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        // 设置标题
        supportActionBar?.title = RenderConfig.TITLE_CONFIG[renderPos]
        // 初始化Fragment
        initFragment()
    }

    private fun initFragment() {
        val fragmentClazz = RenderConfig.FRAGMENT_CONFIG[renderPos]
        fragmentClazz.constructors.forEach {
            if (it.parameters.isEmpty()) {
                val fragment: BaseGLFragment = it.call()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}