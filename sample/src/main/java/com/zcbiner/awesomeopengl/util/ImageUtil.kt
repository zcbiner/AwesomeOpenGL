package com.zcbiner.awesomeopengl.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.Closeable
import java.io.InputStream
import java.lang.Exception

object ImageUtil {
    private const val TAG = "ImageUtil"

    fun loadFromResource(context: Context, resId: Int): Bitmap? {
        val inputStream: InputStream = context.resources.openRawResource(resId)
        try {
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e(TAG, "[loadFromResource] error.", e)
        } finally {
            closeQuietly(inputStream)
        }
        return null
    }

    fun closeQuietly(closeable: Closeable?) {
        if (closeable == null) {
            return
        }
        try {
            closeable.close()
        } catch (e: Exception) {
            Log.e(TAG, "[closeQuietly] error.", e)
        }
    }
}