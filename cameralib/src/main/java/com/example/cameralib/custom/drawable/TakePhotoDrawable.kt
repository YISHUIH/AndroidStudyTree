package com.example.cameralib.custom.drawable

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.cameralib.util.LogUtil
import kotlin.math.min

/**
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/30 17:17    <br>
 * Description: 拍照按钮   <br>
 */
abstract class TakePhotoDrawable : Drawable(), LifecycleObserver {
    companion object {
        const val STATUS_TAKE_PHOTO = 0
        const val STATUS_TAKE_VIDEO = 1
        //开始录像
        const val STATUS_TAKE_VIDEO_START = 2

        //录制进度的stroke的宽度

        const val STROKE_WIDTH = 10.0f
    }

    //内圆半径
    private var innerRadius = 0.0f
        set(value) {
            outRadius += field - value
            field = value
            invalidateSelf()
        }
    private var outRadius = 0.0f
    private var centerX = 0.0f

    //当前状态，默认为拍照
    var defaultStatus = STATUS_TAKE_PHOTO
        set(value) {
            field = value
            if (field == STATUS_TAKE_VIDEO) {
                //开始缩放动画
                startAnim()
            }
            if (field == STATUS_TAKE_VIDEO_START) {
                //开始录像动画
                startAnimForVideo()
            }

        }
    private val paint = Paint()
    private val videoPaint = Paint()

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL

        videoPaint.isAntiAlias = true
        videoPaint.color =  Color.parseColor("#00AAFF")
        videoPaint.style = Paint.Style.STROKE
        videoPaint.strokeWidth = STROKE_WIDTH
    }


    override fun draw(canvas: Canvas) {
        //绘制大小圆
        paint.color = Color.parseColor("#80ffffff")
        canvas.drawCircle(centerX, centerX, outRadius, paint)
        paint.color = Color.WHITE
        canvas.drawCircle(centerX, centerX, innerRadius, paint)
        if (defaultStatus == STATUS_TAKE_VIDEO_START) {
            //绘制录制进度
            canvas.drawArc(rectF, -90.0f, mVideoSweepAngle, false, videoPaint)
        }
    }

    private lateinit var rectF: RectF

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        val width = bounds.right - bounds.left
        val height = bounds.bottom - bounds.top

        centerX = min(width, height).toFloat() / 2
        //绘制录制进度的时候，需要缩小范围，不然显示不全
        rectF = RectF(STROKE_WIDTH / 2, STROKE_WIDTH / 2, centerX * 2 - STROKE_WIDTH / 2, centerX * 2 - STROKE_WIDTH / 2)

        resetRadius()

    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }


    /**
     * 重置内外圆的半径
     */
    private fun resetRadius() {
        innerRadius = centerX / 2

        outRadius = innerRadius + innerRadius / 2
    }


    private var animRadiusChange = ValueAnimator()
    /**
     * 动画
     */
    private fun startAnim() {
        if (innerRadius <= centerX / 4) {
            innerRadius = centerX / 4
            return
        }

        if (defaultStatus == STATUS_TAKE_PHOTO) {
            //拍照动画
            animRadiusChange.setFloatValues(innerRadius, innerRadius / 2, innerRadius)
            animRadiusChange.duration = 500
        } else {
            //录像动画
            animRadiusChange.setFloatValues(innerRadius, innerRadius / 2)
            animRadiusChange.duration = 1000
        }

        animRadiusChange.addUpdateListener {
            innerRadius = it.animatedValue as Float

        }
        animRadiusChange.doOnCancel {
            LogUtil.e("takePhotoDrawable", "doOnCancel")
        }
        animRadiusChange.doOnEnd {
            LogUtil.e("takePhotoDrawable", "doOnEnd")
            //动画播放结束
            if (defaultStatus == STATUS_TAKE_VIDEO) {
                //如果是录像的话开始录像

                defaultStatus = STATUS_TAKE_VIDEO_START
            }
        }

        animRadiusChange.start()
    }

    private var animVideoing = ValueAnimator()
    private var cdt = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            LogUtil.e("sssssssss", "${10 - millisUntilFinished / 1000}")
        }

        override fun onFinish() {
            onStop()
        }

    }
    var mVideoTime: Long = 10000
    var mVideoSweepAngle: Float = 0.0f
        set(value) {
            field = value
            invalidateSelf()
        }

    /**
     * 绘制刻度的动画
     */
    private fun startAnimForVideo() {
        animVideoing.setFloatValues(0.0f, 360.0f)

        animVideoing.duration = mVideoTime
        animVideoing.addUpdateListener {
            mVideoSweepAngle = it.animatedValue as Float
        }

        animVideoing.doOnStart {
            LogUtil.e("TakePhotoDrawables", "doOnStart")
            //开始计时
            cdt.start()
            startVideo()
        }
        animVideoing.doOnEnd {
            LogUtil.e("TakePhotoDrawables", "doOnEnd")
            onStop()
        }
        animVideoing.doOnCancel {
            LogUtil.e("TakePhotoDrawables", "doOnCancel")
            defaultStatus = STATUS_TAKE_PHOTO
            resetRadius()
            stopVideo()
        }
        animVideoing.start()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        LogUtil.e("TakePhotoDrawable", "onStop")
        if (animRadiusChange.isRunning) {
            animRadiusChange.cancel()
        }
        if (animVideoing.isRunning) {
            animVideoing.cancel()
        }
        cdt.cancel()
    }

    abstract fun startVideo()
    abstract fun stopVideo()
}