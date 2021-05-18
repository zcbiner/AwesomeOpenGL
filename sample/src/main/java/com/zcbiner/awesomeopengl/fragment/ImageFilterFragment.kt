package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.ViewGroup
import android.widget.CompoundButton
import com.zcbiner.awesomeopengl.R
import com.zcbiner.awesomeopengl.gl.GLShowSurfaceView
import com.zcbiner.awesomeopengl.gl.render.BaseRender
import com.zcbiner.awesomeopengl.gl.render.GaussFilterRender
import com.zcbiner.awesomeopengl.gl.render.GrayFilterRender
import com.zcbiner.awesomeopengl.gl.render.MosaicFilterRender
import kotlinx.android.synthetic.main.fragment_image_filter.*

/**
 * 请填写类描述
 */
class ImageFilterFragment: BaseGLFragment(), CompoundButton.OnCheckedChangeListener {

    private var selectedRadio = 0

    override fun initGLView(rootView: ViewGroup) {
        grayBtn.setOnCheckedChangeListener(this)
        gaussBtn.setOnCheckedChangeListener(this)
        mosaicBtn.setOnCheckedChangeListener(this)
        grayBtn.isChecked = true
    }

    override fun provideLayout(): Int {
        return R.layout.fragment_image_filter
    }
    override fun provideRender(context: Context): BaseRender {
        Log.d("corbinzhong", "provideRender id=${selectedRadio}")
        return when (selectedRadio) {
            R.id.gaussBtn -> {
                GaussFilterRender(requireContext())
            }
            R.id.mosaicBtn -> {
                MosaicFilterRender(requireContext())
            }
            else -> {
                GrayFilterRender(requireContext())
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (!isChecked) {
            return
        }
        Log.d("corbinzhong", "onCheckedChanged id=${buttonView.id}")
        selectedRadio = buttonView.id
        glViewContainer.removeAllViews()
        glView = GLShowSurfaceView(requireContext())
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        glViewContainer.addView(glView, lp)
        val render = provideRender(requireContext())
        glView.setEGLContextClientVersion(2)
        glView.setRenderer(render)

        // 设置GLSurfaceView的渲染模式为GLSurfaceView.RENDERMODE_CONTINUOUSLY或不设置时，系统就会主动回调onDrawFrame()方法.
        // 如果设置为 RENDERMODE_WHEN_DIRTY ，手动调用requestRender()，才会渲染。
        glView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}