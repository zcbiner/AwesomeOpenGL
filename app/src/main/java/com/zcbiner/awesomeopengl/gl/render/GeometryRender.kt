package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 用于绘制简单的几何图形。
 */
class GeometryRender(private val context: Context): BaseRender() {

    companion object {
        // 每一次取点的时候取几个点。
        private const val COORDS_PER_VERTEX = 3
        // 绘制的坐标。三角形的三个顶点的坐标
        private val TRIANGLE_COORDS = floatArrayOf(
            0.0f, 0.8f, 0.0f,
            -0.5f, 0.2f, 0.0f,
            0.5f, 0.2f, 0.0f
        )

        // 绘制的颜色，分别是RGBA的值。
        private val COLOR = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

        // 4bytes per vertex.
        private const val VERTEX_STRIDE = COORDS_PER_VERTEX * 4

        private val VERTEX_COUNT = TRIANGLE_COORDS.size / COORDS_PER_VERTEX
    }

    // 坐标本地内存地址
    private lateinit var vertexBuffer: FloatBuffer
    private var program: Int = 0

    private var positionHandle: Int = 0
    private var colorHandle: Int = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        vertexBuffer = ByteBuffer.allocateDirect(TRIANGLE_COORDS.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(TRIANGLE_COORDS)
        vertexBuffer.position(0)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("geometry_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets("geometry_frag.glsl", context.resources)

        program = ShaderUtils.createProgram(vertexSource, fragSource)
        if (program > 0) {
            positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
            colorHandle = GLES20.glGetUniformLocation(program, "vColor")
        }
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        // 使顶点属性数组有效
        GLES20.glEnableVertexAttribArray(positionHandle)
        // 为顶点属性赋值
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, VERTEX_STRIDE, vertexBuffer)
        // 设置颜色
        GLES20.glUniform4fv(colorHandle, 1, COLOR, 0)
        // 绘制图形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, VERTEX_COUNT)
        // 禁用顶点数组
        GLES20.glDisableVertexAttribArray(positionHandle)
    }

}