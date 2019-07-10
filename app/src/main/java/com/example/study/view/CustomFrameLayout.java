package com.example.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/5 14:12    <br>
 * Description: 自定义FrameLayout，查看事件分发情况   <br>
 */
public class CustomFrameLayout extends FrameLayout {
    private static final String TAG = "CustomFrameLayout";

    public CustomFrameLayout(Context context) {
        super(context);
        Log.e(TAG,"CustomFrameLayout");
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG,"CustomFrameLayout");
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG,"CustomFrameLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,"onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.e(TAG,"requestLayout");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG,"dispatchTouchEvent"+"  action:"+ev.getAction());
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent"+"  action:"+ev.getAction());
        if (ev.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent"+"  action:"+event.getAction());
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            return true;
        }
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }
        return super.onTouchEvent(event);
    }
}
