package com.example.cameralib.custom.drawable

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/29 15:09    <br>
 * Description: 闪光灯开关   <br>
 */
class FlashStatusDrawable : Drawable() {
    companion object{
        /**
         * 打开闪光灯
         */
        const val STATUS_ON = 1
        /**
         * 关闭
         */
        const val STATUS_OFF = 0
    }

    var status = STATUS_OFF
        set(value) {
            field = value
            invalidateSelf()
        }


    var centerX: Float = 0.0f
    var centerY: Float = 0.0f

    private val paint = Paint()
    val path = Path()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = 2.5f
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = Color.WHITE
    }


    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.translate(centerX + 10, centerY + 10)
        path.reset()
        path.moveTo(centerX / 2, -centerY)
        path.lineTo(centerX / 7, -centerY / 7)
        path.lineTo(centerX, 0.0f)
        path.lineTo(-centerX / 2, centerY)
        path.lineTo(-centerX / 7, centerY / 7)
        path.lineTo(-centerX, 0.0f)
        path.close()
        canvas.drawPath(path, paint)
        if (status == STATUS_OFF) {
            path.reset()
            path.moveTo(-centerX, -centerY)
            path.lineTo(centerX, centerY)
            paint.strokeWidth = 5.0f
            canvas.drawPath(path, paint)
        }
        canvas.restore()
    }


    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val width = bounds.right - bounds.left
        val height = bounds.bottom - bounds.top
        centerX = width.toFloat() / 2 - 10
        centerY = height.toFloat() / 2 - 10
    }

    override fun setAlpha(alpha: Int) {
    }


    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }


}