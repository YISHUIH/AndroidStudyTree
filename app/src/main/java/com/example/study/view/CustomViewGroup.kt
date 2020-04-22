package com.example.study.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlin.math.abs

/**
 * Date: 2019/7/30 16:36    <br>
 * Description:    <br>
 */
class CustomViewGroup : ViewGroup {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.e("child", "onMeasure")
        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        childCenterX = width / 2
    }


    var childCenterX = 0
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e("child", "onLayout")
        childCenterX -= a.toInt()
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            val lp = child.layoutParams as LayoutParams
            val left = childCenterX - child.measuredWidth / 2 + lp.marginStart
            val top = height / 2 - child.measuredHeight / 2
            val right = left + child.measuredWidth - lp.topMargin
            val bottom = top + child.measuredHeight + lp.bottomMargin

            setChildLayoutParams(child, index)
            child.layout(left, top, right, bottom)
            if (index != childCount - 1)
                childCenterX += child.measuredWidth / 2 + getChildAt(index + 1).measuredWidth / 2
        }
    }

    var centerIndx:Int=0
    private fun setChildLayoutParams(child: View, index: Int) {

        val lp = child.layoutParams as LayoutParams
        val scale=1.0-1.0* abs(childCenterX-width/2)/width/2*2
        val r=1.0* abs(childCenterX-width/2)/width/2*2
        lp.scale=scale.toFloat()
        child.scaleX=scale.toFloat()
        child.scaleY=scale.toFloat()
        if (childCenterX-width/2>0){
            //右边
            child.rotationY=r.toFloat()*90
        }else{
            //左边
            child.rotationY=-r.toFloat()*90
        }

        if ((1.0-scale)<0.05){
            //当前中间的位置
            centerIndx=index
            Log.e("sssss", "index  :$index")
        }
        Log.e("sssss", "scale  :$scale")

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("sssss", "onTouchEvent")
        g.onTouchEvent(event)
        return true
    }

    var a = 0f
    val g = GestureDetector(context, object : SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            a += distanceX
            Log.e("sssss", "onScroll     centerChildX:$a")
            requestLayout()
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    })

    fun sd(view: View, points: FloatArray): Boolean {
        points[0] += -view.left.toFloat()
        points[1] += -view.top.toFloat()
        val matrix = view.matrix

        if (!matrix.isIdentity) {
            matrix.invert(matrix)
            matrix.mapPoints(points)
        }
        //判断坐标点是否在view范围内
        Log.e("click", "points[0]: ${points[0]}   points[1]: ${points[1]}")
        Log.e("click", "view.width: ${view.width}    view.height: ${view.height}")
        return points[0] >= 0 && points[1] >= 0 && points[0] < view.width && points[1] < view.height
    }

    public override fun detachViewFromParent(index: Int) {
        super.detachViewFromParent(index)
        Log.e("child", "detachViewFromParent")
    }

    public override fun attachViewToParent(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.attachViewToParent(child, index, params)
        Log.e("child", "attachViewToParent")
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): LayoutParams {
        return LayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    class LayoutParams : MarginLayoutParams {
        var scale = 0f
        var alpha = 0f

        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)
        constructor(width: Int, height: Int) : super(width, height)
        constructor(source: MarginLayoutParams?) : super(source)
        constructor(source: ViewGroup.LayoutParams?) : super(source)


    }
}