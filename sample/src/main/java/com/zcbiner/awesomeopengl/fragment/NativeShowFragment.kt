package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import com.zcbiner.awesomeopengl.R
import com.zcbiner.awesomeopengl.util.ImageUtil
import com.zcbiner.gles.render.NativeRender
import java.nio.ByteBuffer

class NativeShowFragment : BaseGLFragment() {

    private lateinit var render: NativeRender

    override fun provideRender(context: Context): GLSurfaceView.Renderer {
        render = NativeRender(requireContext(), 1)
        loadImage()
        return render
    }

    override fun onDestroy() {
        render.destroy()
        super.onDestroy()
    }

    private fun loadImage() {
        val bitmap = ImageUtil.loadFromResource(requireContext(), R.drawable.texture_demo) ?: return
        Log.d("bugFix", "bitmap is not null")
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        render.setImageData(bitmap.width, bitmap.height, byteBuffer.array())
    }
}