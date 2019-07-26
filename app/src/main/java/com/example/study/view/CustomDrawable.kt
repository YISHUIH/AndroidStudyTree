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
    var path = Path()

    init {
        Log.e("CustomDrawable", "init")
        paint.color = Color.RED
        paint.style=Paint.Style.STROKE
        paint.isAntiAlias=true
        paint.strokeWidth=20f
    }

    override fun draw(canvas: Canvas) {
        Log.e("CustomDrawable", "draw")
        path.quadTo(200f, 200f, 300f, 300f)
        path.quadTo(400f, 400f, 500f, 300f)
        canvas.drawPath(path, paint)

    }

    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        Log.e("CustomDrawable", "onBoundsChange")
    }

    override fun setBounds(bounds: Rect) {
        super.setBounds(bounds)
        Log.e("CustomDrawable", "setBounds")
    }

    override fun setAlpha(alpha: Int) {
        Log.e("CustomDrawable", "setAlpha")
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