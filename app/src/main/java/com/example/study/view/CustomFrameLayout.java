package com.example.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.study.util.LogUtil;

/**
 * Date: 2019/7/5 14:12    <br>
 * Description: 自定义FrameLayout，查看事件分发情况   <br>
 */
public class CustomFrameLayout extends FrameLayout {
    private static final String TAG = "CustomFrameLayout";

    public CustomFrameLayout(Context context) {
        super(context);
        LogUtil.Companion.e(TAG,"CustomFrameLayout");
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.Companion.e(TAG,"CustomFrameLayout");
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.Companion.e(TAG,"CustomFrameLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.Companion.e(TAG,"onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.Companion.e(TAG,"onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.Companion.e(TAG,"onDraw");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        LogUtil.Companion.e(TAG,"requestLayout");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.Companion.e(TAG,"dispatchTouchEvent"+"  action:"+ev.getAction());
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.Companion.e(TAG,"onInterceptTouchEvent"+"  action:"+ev.getAction());
        if (ev.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.Companion.e(TAG,"onTouchEvent"+"  action:"+event.getAction());
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            return true;
        }
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onTouchEvent(event);
    }
}
