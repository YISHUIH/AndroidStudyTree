package com.example.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.study.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/3 10:15    <br>
 * Description: 显示大图   <br>
 */
public class ShowLargeBitmap extends View {

    private static final String TAG = "ShowLargeBitmap";
    /**
     * bitmap参数
     */
    private BitmapFactory.Options mOptions;
    /**
     * bitmap区域展示
     */
    private BitmapRegionDecoder mDecoder;

    /**
     * bitmap对象
     */
    private Bitmap mBitmap;
    /**
     * 默认中心点
     */
    private static final int SHOW_DEFAULT_DIVSOR = 2;

    /**
     * 原图宽
     */
    private int outWidth;
    /**
     * 原图高
     */
    private int outHeight;
    /**
     * 展示的范围
     */
    private Rect mRect;


    /**
     * 画布矩阵，主要用来缩放
     */
    private Matrix mMatrix;
    /**
     * x,y,方向缩放值
     */
    private float mSX;
    private float mSY;

    private static final float MIN_SX = 1.0f;
    private static final float MIN_SY = 1.0f;

    private int mCenterX;
    private int mCenterY;
    /**
     * 控件自身的中心点
     */
    private int centerViewX;
    private int centerViewY;


    public ShowLargeBitmap(Context context) {
        super(context);
    }

    public ShowLargeBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowLargeBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.Companion.e(TAG, "onMeasure");
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        centerViewX = viewWidth / 2;
        centerViewY = viewHeight / 2;
        initRect();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 初始化Bitmap展示区域
     */
    private void initRect() {
        mRect.bottom = mCenterY + centerViewY;
        mRect.right = mCenterX + centerViewX;
        mRect.top = mCenterY - centerViewY;
        mRect.left = mCenterX - centerViewX;
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        LogUtil.Companion.e(TAG, "layout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.Companion.e(TAG, "onDraw");
        if (mDecoder == null) {
            return;
        }

        if (mOptions == null) {
            return;
        }

        if (mOptions.inJustDecodeBounds) {
            mOptions.inJustDecodeBounds = false;
        }

        mBitmap = mDecoder.decodeRegion(mRect, mOptions);

        canvas.drawBitmap(mBitmap, mMatrix, null);

    }

    /**
     * 设置数据源
     *
     * @param mInputStream
     */
    public void setmInputStream(InputStream mInputStream) {
        LogUtil.Companion.e(TAG, "setmInputStream");
        if (mOptions == null) {
            mOptions = new BitmapFactory.Options();
        }
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(mInputStream, null, mOptions);
        //传入的流确定图片尺寸
        outWidth = mOptions.outWidth;
        outHeight = mOptions.outHeight;
        mCenterX = outWidth / SHOW_DEFAULT_DIVSOR;
        mCenterY = outHeight / SHOW_DEFAULT_DIVSOR;

        //实例化初始展示范围
        mRect = new Rect();

        try {
            /**
             * 实例化mDecoder
             */
            mDecoder = BitmapRegionDecoder.newInstance(mInputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.Companion.e(TAG, "IOException:  " + e.getMessage());
        }

        //实例化矩阵
        mMatrix = new Matrix();
    }

    /**
     * 设置缩放比例
     *
     * @param sx
     * @param sy
     */
    public void scaleBitmap(float sx, float sy) {
        mSX += sx;
        mSY += sy;
        //小于最小值，设置为最小值
        if (mSY < MIN_SY) {
            mSY = MIN_SY;
        }
        if (mSX < MIN_SX) {
            mSX = MIN_SX;
        }
        mMatrix.setScale(mSX, mSY);
        invalidate();
    }


    private void setCenter(float offestX, float offestY) {


        setmCenterX((int) offestX);
        setmCenterY((int) offestY);
        if (mRect == null) {
            return;
        }
        initRect();
        invalidate();
    }

    /**
     * 设置中心Bitmap的X值
     *
     * @param offestX
     */
    private void setmCenterX(int offestX) {
        if (Math.abs(offestX) < 5) {
            return;
        }
        this.mCenterX += offestX;
        if (mCenterX < centerViewX) {
            mCenterX = centerViewX;
            return;
        }
        if (outWidth - mCenterX < centerViewX) {
            mCenterX = outWidth - centerViewX;
        }

    }

    /**
     * 设置中心Bitmap的Y值
     *
     * @param offestY
     */
    private void setmCenterY(int offestY) {
        if (Math.abs(offestY) < 5) {
            return;
        }
        this.mCenterY += offestY;
        if (mCenterY < centerViewY) {
            mCenterY = centerViewY;
            return;
        }

        if (outHeight - mCenterY < centerViewY) {
            mCenterY = outHeight - centerViewY;
        }
    }

    float downX = 0;
    float downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float offsetX = x - downX;
                float offsetY = y - downY;
                downX = x;
                downY = y;
                setCenter(-offsetX, -offsetY);
                return true;
            case MotionEvent.ACTION_UP:
                break;
            default:
        }
        return false;
    }

    public synchronized void recycle() {
        mRecycled = true;
        if (mDecoder == null) {
            return;
        }
        mDecoder.recycle();
        if (mBitmap == null) {
            return;
        }
        mBitmap.recycle();

    }

    private boolean mRecycled;

    public boolean isRecycled() {
        return mRecycled;
    }
}
