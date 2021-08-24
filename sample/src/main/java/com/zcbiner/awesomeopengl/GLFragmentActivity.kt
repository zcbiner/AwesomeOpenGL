package com.zcbiner.awesomeopengl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.zcbiner.awesomeopengl.fragment.NativeShowFragment
import com.zcbiner.gles.render.RenderType

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
        // 设置标题
        supportActionBar?.title = RenderType.values()[renderPos].value
        // 初始化Fragment
        initFragment()
    }

    private fun initFragment() {
        val nativeShowFragment = NativeShowFragment()
        val arguments = Bundle()
        arguments.putInt(NativeShowFragment.KEY_TYPE_INDEX, renderPos)
        when(renderPos) {
            1 -> {
                arguments.putInt(NativeShowFragment.KEY_IMAGE_ID, R.drawable.texture_demo)
            }
        }
        nativeShowFragment.arguments = arguments
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, nativeShowFragment)
            .commitAllowingStateLoss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}