package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import com.zcbiner.awesomeopengl.R
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 滤镜Render基类。
 */
abstract class BaseImageFilterRender(context: Context): BaseRender(context) {

    companion object {
        private const val TEXTURE_COORDINATE_COMPONENT_COUNT = 2
        private const val VERTEX_COMPONENT_COUNT = 2
    }

    // 纹理顶点数据
    private val vertexData = floatArrayOf(-1f, -1f, -1f, 1f, 1f, 1f, -1f, -1f, 1f, 1f, 1f, -1f)
    private lateinit var vertexDataBuffer : FloatBuffer

    // 纹理坐标
    private val textureCoordinateData = floatArrayOf(0f, 1f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 1f, 1f)
    private lateinit var textureCoordinateDataBuffer : FloatBuffer

    private var program: Int = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("image_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets(getFragShader(), context.resources)
        program = ShaderUtils.createProgram(vertexSource, fragSource)


        // 将三角形顶点数据放入buffer中
        vertexDataBuffer = ByteBuffer.allocateDirect(vertexData.size * java.lang.Float.SIZE / 8)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexDataBuffer.put(vertexData)
        vertexDataBuffer.position(0)

        // 获取字段a_position在shader中的位置
        val aPositionLocation = GLES20.glGetAttribLocation(program, "a_position")

        // 启动对应位置的参数
        GLES20.glEnableVertexAttribArray(aPositionLocation)

        // 指定a_position所使用的顶点数据
        GLES20.glVertexAttribPointer(aPositionLocation, VERTEX_COMPONENT_COUNT, GLES20.GL_FLOAT, false,0, vertexDataBuffer)

        // 将纹理坐标数据放入buffer中
        textureCoordinateDataBuffer = ByteBuffer.allocateDirect(textureCoordinateData.size * java.lang.Float.SIZE / 8)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        textureCoordinateDataBuffer.put(textureCoordinateData)
        textureCoordinateDataBuffer.position(0)

        // 获取字段a_textureCoordinate在shader中的位置
        val aTextureCoordinateLocation = GLES20.glGetAttribLocation(program, "a_textureCoordinate")

        // 启动对应位置的参数
        GLES20.glEnableVertexAttribArray(aTextureCoordinateLocation)

        // 指定a_textureCoordinate所使用的顶点数据
        GLES20.glVertexAttribPointer(aTextureCoordinateLocation, TEXTURE_COORDINATE_COMPONENT_COUNT, GLES20.GL_FLOAT, false,0, textureCoordinateDataBuffer)


        // 创建图片纹理
        val textures = IntArray(1)
        GLES20.glGenTextures(textures.size, textures, 0)
        val imageTexture = textures[0]

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, imageTexture)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)


        // 要加载的图片
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.texture_demo
        )

        val b = ByteBuffer.allocate(bitmap.width * bitmap.height * 4)
        bitmap.copyPixelsToBuffer(b)
        b.position(0)
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, bitmap.width, bitmap.height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, b)
        val uTextureLocation = GLES20.glGetAttribLocation(program, "u_texture")
        GLES20.glUniform1i(uTextureLocation, 0)
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)

        // 调用draw方法用TRIANGLES的方式执行渲染，顶点数量为3个
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexData.size / VERTEX_COMPONENT_COUNT)
    }

    abstract fun getFragShader(): String
}