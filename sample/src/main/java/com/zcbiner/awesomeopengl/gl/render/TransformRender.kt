package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.opengl.GLES20
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 用于学习怎么进行变换（scale，rotate，translate）。
 */
class TransformRender(context: Context): BaseRender(context) {

    private val vertexArray = floatArrayOf(0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f)
    private var programId = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("transform_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets("transform_frag.glsl", context.resources)
        programId = ShaderUtils.createProgram(vertexSource, fragSource)

        val buffer = ByteBuffer.allocateDirect(vertexArray.size * java.lang.Float.SIZE)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        buffer.put(vertexArray)
        buffer.position(0)

        initVertexParams(buffer)
    }

    /**
     * 给顶点着色器的参数赋值
     */
    private fun initVertexParams(buffer: FloatBuffer) {
        // 设置顶点数据
        val position = GLES20.glGetAttribLocation(programId, "a_Position")
        GLES20.glEnableVertexAttribArray(position)
        GLES20.glVertexAttribPointer(position, 2, GLES20.GL_FLOAT, false, 0, buffer)

        // 设置位移
        val translatePos = GLES20.glGetUniformLocation(programId, "u_Translate")
        GLES20.glEnableVertexAttribArray(translatePos)
        GLES20.glUniform2f(translatePos, 0.1f, 0.1f)

        // 设置缩放
        val scalePos = GLES20.glGetUniformLocation(programId, "u_Scale")
        GLES20.glEnableVertexAttribArray(scalePos)
        GLES20.glUniform1f(scalePos, 0.5f)

        // 设置旋转
        val rotatePos = GLES20.glGetUniformLocation(programId, "u_Rotate")
        GLES20.glEnableVertexAttribArray(rotatePos)
        GLES20.glUniform1f(rotatePos, Math.toRadians(45.0).toFloat())
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexArray.size / 2)
    }

}