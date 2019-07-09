package com.example.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/5 14:24    <br>
 * Description: 自定义Button，查看事件分发机制   <br>
 */
public class CustomView extends View {
    private static final String TAG = "CustomView";
    /**
     * 标题
     */
    private String[] mTitles;
    /**
     * 画线的画笔
     */
    private Paint mLinePaint;

    /**
     * 画标题的画笔
     */
    private Paint mTitlePaint;

    /**
     * view的中心点
     */
    int mCenterX;
    int mCenterY;

    /**
     * 文本中心点初始值Y
     */
    int mCenterTextDefalutY;

    /**
     * 文本的中心点Y
     */
    int mCenterTextY;

    /**
     * 文字之间的间隔
     */
    private final int TEXT_LINE_SPACE = 40;

    /**
     * 文字和线之间的间隔
     */
    private final int LINE_SPACE = TEXT_LINE_SPACE / 2;

    private int currentIndex;

    /**
     * @param context
     */
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTitles = new String[]{"第一个标题", "第二个标题", "第三个标题", "第四个标题", "第五个标题", "第六个标题", "第七个标题", "第八个标题", "第九个标题", "第十个标题", "第11个标题", "第12个标题"};

        //划线的画笔
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(10);
        mLinePaint.setColor(Color.RED);
        //画标题的画笔
        mTitlePaint = new Paint();
        mTitlePaint.setTextSize(64);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = viewWidth / 2;
        mCenterY = viewHeight / 2;

        mCenterTextDefalutY = mCenterY;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout");
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.e(TAG, "layout");
    }

    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");
        int titleWidth = getTextWidth(mTitles[0]);
        int titleHeight = getTextHeight(mTitles[0]);
        height = titleHeight;
        mCenterTextY = mCenterTextDefalutY;
        for (String title : mTitles) {
            canvas.drawText(title, mCenterX, mCenterTextY + (float) titleHeight / 4, mTitlePaint);
            if (title.equals(mTitles[mTitles.length - 1])) {
                break;
            }
            mCenterTextY += titleHeight + TEXT_LINE_SPACE;
        }
        //上面的线
        drawTopLine(canvas, titleWidth, titleHeight);

        drawBottomLine(canvas, titleWidth, titleHeight);
        canvas.drawCircle(mCenterX, mCenterTextY, 10, mTitlePaint);
        canvas.drawCircle(mCenterX, mCenterY, 10, mLinePaint);
    }

    private void drawTopLine(Canvas canvas, int titleWidth, float titleHeight) {
        float startX = mCenterX - (float) titleWidth / 2;
        float startY = mCenterY - titleHeight / 2 - LINE_SPACE;
        float stopX = startX + titleWidth;
        canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
    }

    private void drawBottomLine(Canvas canvas, int titleWidth, float titleHeight) {
        float startX = mCenterX - (float) titleWidth / 2;
        float startY = mCenterY + titleHeight / 2 + LINE_SPACE;
        float stopX = startX + titleWidth;
        canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
    }

    private Rect getTextBounds(String title) {
        Rect bounds = new Rect();
        mTitlePaint.getTextBounds(title, 0, title.length(), bounds);
        return bounds;
    }

    private int getTextWidth(String title) {
        Rect textBounds = getTextBounds(title);
        return textBounds.right - textBounds.left;
    }

    private int getTextHeight(String title) {
        Rect textBounds = getTextBounds(title);
        return textBounds.bottom - textBounds.top;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent" + "  action: " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    float x;
    float y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {


            float v = event.getY() - y;
            y = event.getY();
            if (v > 0) {
                //已经显示的是第一条，禁止下滑
                if (mCenterTextDefalutY - mCenterY >= 0) {
                    mCenterTextDefalutY = mCenterY;
                    invalidate();
                    return true;
                }
            }

            if (v < 0) {
                //已经显示的是最后一条，禁止上滑
                Log.e(TAG, "mCenterTextY:  " + mCenterTextY);
                Log.e(TAG, "mCenterY:  " + mCenterY);
                Log.e(TAG, "mCenterTextDefalutY:  " + mCenterTextDefalutY);
                if (mCenterTextY - mCenterY <= 0) {
                    mCenterTextDefalutY = mCenterY-((mTitles.length-1)*(height+TEXT_LINE_SPACE));
                    invalidate();
                    return true;
                }
            }
            mCenterTextDefalutY += v;
            invalidate();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int offset=mCenterY-mCenterTextDefalutY;

            int i = offset / (height + TEXT_LINE_SPACE);
            int i1 = offset % (height + TEXT_LINE_SPACE);
            if (i1>(height + TEXT_LINE_SPACE)){
                i++;
            }
            currentIndex=i;
            mCenterTextDefalutY = mCenterY-(i*(height+TEXT_LINE_SPACE));
            invalidate();
            Log.e(TAG, "i:  " + i);
            Log.e(TAG, "i1:  " + i1);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "performClick");
        return super.performClick();
    }
}
