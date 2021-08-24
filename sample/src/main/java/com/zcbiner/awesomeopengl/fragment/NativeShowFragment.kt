package com.zcbiner.awesomeopengl.fragment

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import com.zcbiner.awesomeopengl.util.ImageUtil
import com.zcbiner.gles.render.NativeRender
import com.zcbiner.gles.render.RenderType
import java.nio.ByteBuffer

class NativeShowFragment : BaseGLFragment() {

    private lateinit var render: NativeRender

    override fun provideRender(context: Context): GLSurfaceView.Renderer {
        val typeIndex = arguments?.getInt(KEY_TYPE_INDEX) ?: 0
        render = NativeRender(requireContext(), RenderType.values()[typeIndex])
        loadImage()
        return render
    }

    override fun onDestroy() {
        render.destroy()
        super.onDestroy()
    }

    private fun loadImage() {
        val imageId = arguments?.getInt(KEY_IMAGE_ID) ?: 0
        if (imageId == 0) {
            Log.i(TAG, "[loadImage] imageId is 0.")
            return
        }
        val bitmap = ImageUtil.loadFromResource(requireContext(), imageId)
        if (bitmap == null) {
            Log.e(TAG, "[loadImage] bitmap is null.")
            return
        }
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        render.setImageData(bitmap.width, bitmap.height, byteBuffer.array())
    }

    companion object {
        private const val TAG = "NativeShowFragment"
        const val KEY_TYPE_INDEX = "render_type_index"
        const val KEY_IMAGE_ID = "image_id"
    }
}