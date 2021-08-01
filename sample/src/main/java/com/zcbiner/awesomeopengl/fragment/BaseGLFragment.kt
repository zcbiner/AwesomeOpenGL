package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zcbiner.awesomeopengl.R

/**
 * 包含一个GLSurfaceView的Fragment，处理了相关的初始化逻辑。
 */
abstract class BaseGLFragment: Fragment() {

    protected lateinit var glView: GLSurfaceView
    private lateinit var rootView: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(provideLayout(), container, false) as ViewGroup
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGLView(rootView)
    }

    protected open fun provideLayout(): Int {
        return R.layout.fragment_base_gl
    }

    protected abstract fun provideRender(context: Context): GLSurfaceView.Renderer

    protected open fun initGLView(rootView: ViewGroup) {
        glView = rootView.findViewById(R.id.glView)
        val render = provideRender(requireContext())
        glView.setEGLContextClientVersion(3)
        glView.setRenderer(render)

        // 设置GLSurfaceView的渲染模式为GLSurfaceView.RENDERMODE_CONTINUOUSLY或不设置时，系统就会主动回调onDrawFrame()方法.
        // 如果设置为 RENDERMODE_WHEN_DIRTY ，手动调用requestRender()，才会渲染。
        glView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
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