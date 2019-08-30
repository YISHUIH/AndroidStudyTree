package com.example.cameralib.custom.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable

/**
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/30 17:17    <br>
 * Description: 拍照按钮   <br>
 */
class TakePhotoDrawable :Drawable(){
    companion object{
        const val STATUS_TAKE_PHOTO=1
        const val STATUS_TAKE_VIDEO=0
    }

    var defaultStatu= STATUS_TAKE_PHOTO
    set(value) {
        field=value
        invalidateSelf()
    }


    override fun draw(canvas: Canvas) {



    }


    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val width=bounds.right-bounds.left
        val height=bounds.bottom-bounds.top



    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }
}