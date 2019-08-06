package com.example.study.view

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/25 14:16    <br>
 * Description: CustomDrawable   <br>
 */
class CustomDrawable : Drawable() {

    val paint: Paint = Paint()
    var leftPath = Path()
    var rightPath = Path()

    lateinit var rect: Rect
    var centerX: Float = 0f
    var centerY: Float = 0f


    init {
        Log.e("CustomDrawable", "init")
        paint.color = Color.GRAY
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
    }

    var rang: Double = .0
        set(value) {
            if (value <= 90.0) {
                field = value
                invalidateSelf()
            }
        }

    override fun draw(canvas: Canvas) {
        Log.e("CustomDrawable", "draw")

        var d = rang / 90
        val f: Double
        f = 300 * d * .5

        Log.e("ffffffff", "$f")

        val leftPoint = getLeftPoint(rang)
        val rightPoint = getRightPoint(rang)
        canvas.drawCircle(centerX, centerY, 5f, paint)
        val y = 150f * Math.sin((0.0 + rang - 20) * Math.PI / 180) + centerY
        val x = 150f * Math.cos((.0 + rang - 20) * Math.PI / 180) + centerX
        leftPath.reset()
        leftPath.moveTo(centerX, centerY)
        leftPath.quadTo(x.toFloat(), y.toFloat(), rightPoint.x.toFloat(), rightPoint.y.toFloat())
        canvas.drawPath(leftPath, paint)


        val y1 = 150f * Math.sin((180.0 - rang + 20) * Math.PI / 180) + centerY
        val x1 = 150f * Math.cos((180.0 - rang + 20) * Math.PI / 180) + centerX
        rightPath.reset()
        rightPath.moveTo(centerX, centerY)
        rightPath.quadTo(x1.toFloat(), y1.toFloat(), leftPoint.x.toFloat(), leftPoint.y.toFloat())
        canvas.drawPath(rightPath, paint)

    }

    fun getLeftPoint(ange: Double): Point {
        val point = Point()
        val y = 300f * Math.sin((180.0 - ange) * Math.PI / 180) + centerY
        val x = 300f * Math.cos((180.0 - ange) * Math.PI / 180) + centerX

        point.x = x.toInt()
        point.y = y.toInt()
        return point
    }

    fun getRightPoint(ange: Double): Point {
        val point = Point()
        val y = 300f * Math.sin((0.0 + ange) * Math.PI / 180) + centerY
        val x = 300f * Math.cos((.0 + ange) * Math.PI / 180) + centerX

        point.x = x.toInt()
        point.y = y.toInt()
        return point
    }

    override fun onLayoutDirectionChanged(layoutDirection: Int): Boolean {
        Log.e("CustomDrawable", "onLayoutDirectionChanged")
        return super.onLayoutDirectionChanged(layoutDirection)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        Log.e("CustomDrawable", "onBoundsChange")
        Log.e("CustomDrawable", "top  :${bounds.top}")
        Log.e("CustomDrawable", "left  :${bounds.left}")
        Log.e("CustomDrawable", "right  :${bounds.right}")
        Log.e("CustomDrawable", "bottom  :${bounds.bottom}")
        rect = bounds
        centerX = rect.centerX().toFloat()
        centerY = rect.centerY().toFloat()

    }

    override fun setBounds(bounds: Rect) {
        super.setBounds(bounds)
        Log.e("CustomDrawable", "setBounds")
    }

    override fun setAlpha(alpha: Int) {
        Log.e("CustomDrawable", "setAlpha    alpha:$alpha")
    }

    @SuppressLint("WrongConstant")
    override fun getOpacity(): Int {
        Log.e("CustomDrawable", "getOpacity")
        return 88
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        Log.e("CustomDrawable", "setColorFilter")
    }


}