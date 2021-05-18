package com.zcbiner.awesomeopengl.gl.render

import android.content.Context
import android.opengl.GLES20
import android.opengl.Matrix
import com.zcbiner.awesomeopengl.gl.util.ShaderUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 绘制一个立方体。
 */
class CubeRender(context: Context): BaseRender(context) {

    private var program = 0

    // MVP矩阵句柄
    private var mvpMatrixLoc = 0
    private var positionLoc = 0

    private var mvpMatrix = FloatArray(16)

    private val r = 0.5f
    // 三维空间的顶点坐标，即立方体的8个顶点
    private val vertexData = floatArrayOf(
        -r, r, r,//0
        -r, -r, r,//1
        r, -r, r,//2
        r, r, r,//3
        r, -r, -r,//4
        r, r, -r,//5
        -r, -r, -r,//6
        -r, r, -r//7
    )


    // 绘制索引。先绘制0，1，2三个点组成的三角形，再绘制0，2，3
    private val indices = shortArrayOf(
        0, 1, 2, 0, 2, 3,
        3, 2, 4, 3, 4, 5,
        5, 4, 6, 5, 6, 7,
        7, 6, 1, 7, 1, 0,
        7, 0, 3, 7, 3, 5,
        6, 1, 2, 6, 2, 4
    )

    private var indexBuffer: ShortBuffer? = null


    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)

        val vertexSource = ShaderUtils.loadGLSLFromAssets("cube_vertex.glsl", context.resources)
        val fragSource = ShaderUtils.loadGLSLFromAssets("cube_frag.glsl", context.resources)
        program = ShaderUtils.createProgram(vertexSource, fragSource)

        mvpMatrixLoc = GLES20.glGetUniformLocation(program, "u_Matrix")
        positionLoc = GLES20.glGetAttribLocation(program, "a_Position")

        val vertexBuffer = ByteBuffer.allocateDirect(vertexData.size * java.lang.Float.SIZE)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData)
        vertexBuffer.position(0)

        indexBuffer = ByteBuffer.allocateDirect(indices.size * java.lang.Short.SIZE)
            .order(ByteOrder.nativeOrder())
            .asShortBuffer()
            .put(indices)
        indexBuffer?.position(0)
        GLES20.glEnableVertexAttribArray(positionLoc)
        GLES20.glVertexAttribPointer(positionLoc, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val modelMatrix = FloatArray(16)
        Matrix.setIdentityM(modelMatrix, 0)
        Matrix.rotateM(modelMatrix,0,45F,0F,1F,0F)
        val viewMatrix = FloatArray(16)
        Matrix.setIdentityM(viewMatrix, 0)
        Matrix.setLookAtM(viewMatrix, 0,
            0F, 5F, 10F,
            0F, 0F, 0F,
            0F, 1F, 0F)
        val projectionMatrix = FloatArray(16)
        Matrix.setIdentityM(projectionMatrix, 0)
        val ratio = width.toFloat() / height
        //设置透视投影
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 20f)
        val mTempMvMatrix = FloatArray(16)
        Matrix.setIdentityM(mTempMvMatrix, 0)
        Matrix.multiplyMM(mTempMvMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, mTempMvMatrix, 0)
        GLES20.glUniformMatrix4fv(mvpMatrixLoc, 1, false, mvpMatrix, 0)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)

        GLES20.glDrawElements(
            GLES20.GL_TRIANGLE_STRIP, indices.size, GLES20.GL_UNSIGNED_SHORT, indexBuffer
        )
    }


}