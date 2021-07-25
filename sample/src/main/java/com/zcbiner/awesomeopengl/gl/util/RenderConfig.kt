package com.zcbiner.awesomeopengl.gl.util

import com.zcbiner.awesomeopengl.fragment.*
import kotlin.reflect.KClass

/**
 * 用于配置标题对应的Render。
 *
 * 标题和Render在数组中的位置需要保持一致，两个数组长度应该保持一致。
 *
 * ps:Kotlin的二维数组太难用了，所以采用两个数组来做。
 */
object RenderConfig {

    val TITLE_CONFIG: Array<String> = arrayOf(
        "简单几何图形",
        "绘制纹理",
        "顶点着色器坐标变换",
        "片段着色器颜色变换",
        "绘制立方体",
        "滤镜效果",
        "水波纹效果",
        "粒子效果",
        "3D模型效果"
    )

    val FRAGMENT_CONFIG: Array<KClass<out BaseGLFragment>> = arrayOf(
        TriangleFragment::class,
        ImageFragment::class,
        TransformFragment::class,
        ColorFragment::class,
        CubeFragment::class,
        ImageFilterFragment::class,
        NativeShowFragment::class
    )
}