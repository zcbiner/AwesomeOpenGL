package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.opengl.GLES20
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class ColorRender(context: Context): BaseRender(context) {

    private val vertexArray = floatArrayOf(0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f)

    // 一个颜色值分别有RGBA
    private val colorData = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 1.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f)

    private var programId = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("color_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets("color_frag.glsl", context.resources)
        programId = ShaderUtils.createProgram(vertexSource, fragSource)

        val buffer = ByteBuffer.allocateDirect(vertexArray.size * java.lang.Float.SIZE)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        buffer.put(vertexArray)
        buffer.position(0)

        // 设置顶点数据
        val position = GLES20.glGetAttribLocation(programId, "a_Position")
        GLES20.glEnableVertexAttribArray(position)
        GLES20.glVertexAttribPointer(position, 2, GLES20.GL_FLOAT, false, 0, buffer)

        val colorBuffer = ByteBuffer.allocateDirect(colorData.size * java.lang.Float.SIZE)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        colorBuffer.put(colorData)
        colorBuffer.position(0)

        // 设置颜色
        val colorPos = GLES20.glGetAttribLocation(programId, "a_Color")
        GLES20.glEnableVertexAttribArray(colorPos)
        GLES20.glVertexAttribPointer(colorPos, 4, GLES20.GL_FLOAT, false,0, colorBuffer)
    }


    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexArray.size / 2)
    }

}