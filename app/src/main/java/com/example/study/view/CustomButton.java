package com.example.study.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.study.util.LogUtil;

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/5 14:24    <br>
 * Description: 自定义Button，查看事件分发机制   <br>
 */
@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {
    private static final String TAG="CustomButton";
    public CustomButton(Context context) {
        super(context);
        LogUtil.Companion.e(TAG,"CustomButton1");
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.Companion.e(TAG,"CustomButton2");
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.Companion.e(TAG,"CustomButton3");
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
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        LogUtil.Companion.e(TAG,"layout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.Companion.e(TAG,"onDraw");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.Companion.e(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.Companion.e(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
