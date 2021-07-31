package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import android.opengl.GLSurfaceView
import com.zcbiner.gles.render.NativeRender

class NativeShowFragment : BaseGLFragment() {
    override fun provideRender(context: Context): GLSurfaceView.Renderer {
        return NativeRender(requireContext(), 0)
    }
}