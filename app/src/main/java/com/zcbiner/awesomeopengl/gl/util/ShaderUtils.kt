package com.zcbiner.awesomeopengl.gl.util

import android.content.res.Resources
import android.opengl.GLES20
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException
import java.io.InputStream

/**
 * 工具类
 */
object ShaderUtils {

    private const val TAG = "ShaderUtils"

    /**
     * 从Assets目录加载GLSL文件并转成String。
     */
    fun loadGLSLFromAssets(name: String, resource: Resources): String? {
        var inputStream: InputStream? = null
        var byteArrayOutputStream: ByteArrayOutputStream? = null
        try {
            inputStream = resource.assets.open(name)
            byteArrayOutputStream = ByteArrayOutputStream()
            var ch = inputStream.read()
            while (ch != -1) {
                byteArrayOutputStream.write(ch)
                ch = inputStream.read()
            }
            val buff = byteArrayOutputStream.toByteArray()
            val result = String(buff, Charsets.UTF_8)
            return result.replace("\\r\\n", "\\n")
        } catch (exception: IOException) {
            Log.d(TAG, "loadGLSLFromAssets error:${exception.message}")
        } finally {
            closeQuietly(byteArrayOutputStream)
            closeQuietly(inputStream)
        }
        return null
    }

    private fun closeQuietly(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (exception: IOException) {
            // do nothing
        }
    }

    /**
     * 加载Shader。
     *
     * @param shaderType must be [GLES20.GL_VERTEX_SHADER] or [GLES20.GL_FRAGMENT_SHADER]
     * @param source Shader的脚本字符串
     */
    fun loadShader(shaderType: Int, source: String): Int {
        // 创建一个Shader
        var shader = GLES20.glCreateShader(shaderType)
        if (shader != 0) {
            // 加载Shader的源代码
            GLES20.glShaderSource(shader, source)
            // 编译Shader
            GLES20.glCompileShader(shader)
            val compiled = IntArray(1)
            // 获取Shader的编译情况
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0)
            if (compiled[0] != GLES20.GL_TRUE) {
                Log.e(TAG, "Could not compile shader $shaderType, message:${GLES20.glGetShaderInfoLog(shader)}")
                GLES20.glDeleteShader(shader)
                shader = 0
            }
        }
        return shader
    }

    /**
     * 创建Shader程序。
     */
    fun createProgram(vertexSource: String?, fragSource: String?): Int {
        if (vertexSource == null || fragSource == null) {
            return 0
        }
        // 加载顶点着色器
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource)
        if (vertexShader == 0) {
            return 0
        }

        // 加载片元着色器
        val fragShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragSource)
        if (fragShader == 0) {
            return 0
        }

        // 创建程序
        var program = GLES20.glCreateProgram()
        // 若程序创建成功则向程序中加入顶点着色器与片元着色器
        if (program != 0) {
            // 向程序中加入顶点着色器
            GLES20.glAttachShader(program, vertexShader)
            // 向程序中加入片元着色器
            GLES20.glAttachShader(program, fragShader)
            // 链接程序
            GLES20.glLinkProgram(program)
            // 存放链接成功program数量的数组
            val linkStatus = IntArray(1)
            // 获取program的链接情况
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0)
            // 若链接失败则报错并删除程序
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e(TAG, "Could not link program: ")
                Log.e(TAG, GLES20.glGetProgramInfoLog(program))
                GLES20.glDeleteProgram(program)
                program = 0
            }
        }
        return program
    }
}