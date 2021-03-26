package com.zcbiner.awesomeopengl.gl.util

import com.zcbiner.awesomeopengl.gl.render.BaseRender
import com.zcbiner.awesomeopengl.gl.render.GeometryRender
import com.zcbiner.awesomeopengl.gl.render.ImageRender
import kotlin.reflect.KClass

/**
 * 用于配置标题对应的Render。
 *
 * 标题和Render在数组中的位置需要保持一致，两个数组长度应该保持一致（未完成）
 *
 * ps:Kotlin的二维数组太难用了，所以采用两个数组来做。
 */
object RenderConfig {

    val TITLE_CONFIG: Array<String> = arrayOf(
        "简单几何图形",
        "绘制纹理",
        "理解坐标变换",
        "高斯模糊",
        "水波纹效果",
        "粒子效果",
        "3D模型效果"
    )

    val RENDER_CONFIG: Array<KClass<out BaseRender>> = arrayOf(
        GeometryRender::class,
        ImageRender::class
    )
}