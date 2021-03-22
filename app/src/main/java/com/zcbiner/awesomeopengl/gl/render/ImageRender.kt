package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.opengl.GLES20
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 用于展示一张图片。在OpenGL中，图片称为纹理。
 */
class ImageRender(private val context: Context): BaseRender() {

    companion object {
        private const val TEXTURE_COORDINATE_COMPONENT_COUNT = 2
        private const val VERTEX_COMPONENT_COUNT = 2
    }

    // 纹理顶点数据
    // The vertex data of the texture
    private val vertexData = floatArrayOf(-1f, -1f, -1f, 1f, 1f, 1f, -1f, -1f, 1f, 1f, 1f, -1f)
    private lateinit var vertexDataBuffer : FloatBuffer

    // 纹理坐标
    // The texture coordinate
    private val textureCoordinateData = floatArrayOf(0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 1f, 1f)
    private lateinit var textureCoordinateDataBuffer : FloatBuffer

    private var program: Int = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("image_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets("image_frag.glsl", context.resources)
        program = ShaderUtils.createProgram(vertexSource, fragSource)


        // 创建图片纹理
        val textures = IntArray(1)
        GLES20.glGenTextures(textures.size, textures, 0)
        val imageTexture = textures[0]

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, imageTexture)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)


    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)

        // 调用draw方法用TRIANGLES的方式执行渲染，顶点数量为3个
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexData.size / VERTEX_COMPONENT_COUNT)
    }

}