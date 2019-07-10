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

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/5 14:24    <br>
 * Description: 自定义Button，查看事件分发机制   <br>
 */
public class CustomView extends View {
    private static final String TAG = "CustomView";

    /**
     * 文字之间的间隔
     */
    private final int TEXT_LINE_SPACE = 40;

    /**
     * 文字和线之间的间隔
     */
    private final int LINE_SPACE = TEXT_LINE_SPACE / 2;
    /**
     * 默认的文字大小
     */
    private final float DEFAULT_TEXT_SIZE = 32;


    /**
     * 标题
     */
    private List<String> mTitles;
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
     * 当前文本的下标
     */
    private int currentIndex;
    /**
     * 文本高度
     */
    private int titleHeight;
    /**
     * 文本和行间距的高度
     */
    private int textTotalHeight;

    /**
     * 是否允许滑动超出范围
     */
    private boolean disAllowOutTopOrBottom = false;

    /**
     * 当前手指所在的坐标
     */
    float x;
    float y;


    /**
     * 选择监听
     */
    private OnItemSelectListener onItemSelectListener;

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
        mTitles = new ArrayList<>();

        //划线的画笔
        mLinePaint = new Paint();

        mLinePaint.setColor(Color.GRAY);
        //画标题的画笔
        mTitlePaint = new Paint();
        mTitlePaint.setTextSize(DEFAULT_TEXT_SIZE);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);

        //文本高度
        titleHeight = (int) DEFAULT_TEXT_SIZE;
        //文本和行间距高度
        textTotalHeight = titleHeight + TEXT_LINE_SPACE;

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
        Log.e(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.e(TAG, "layout");
        super.layout(l, t, r, b);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw");
        super.onDraw(canvas);

        //没有数据就返回
        if (mTitles.size() == 0) {
            return;
        }
        if (currentIndex < 0) {
            currentIndex = 0;
        }
        if (currentIndex >= mTitles.size()) {
            currentIndex = mTitles.size() - 1;
        }


        //整体的偏移量
        int offset = mCenterY - mCenterTextDefalutY;
        //偏移了多少个文本的高度

        int i = offset / textTotalHeight;

        int i1 = offset % (textTotalHeight);
        //如果超过余下的，大于一个高度，就加一
        if (i1 > textTotalHeight / 2) {
            i++;
        }
        currentIndex = i;


        mCenterTextY = mCenterTextDefalutY;
        for (String title : mTitles) {
            int index = mTitles.indexOf(title);
            if (index == currentIndex) {
                //当前文本设置颜色
                mTitlePaint.setColor(Color.BLACK);
                //设置回调。
                if (onItemSelectListener != null) {
                    onItemSelectListener.onItemSelect(currentIndex);
                }
            } else {
                //其它文本设置颜色
                mTitlePaint.setColor(Color.GRAY);
            }
            canvas.drawText(title, mCenterX, mCenterTextY + (float) titleHeight / 4, mTitlePaint);
            if (title.equals(mTitles.get(mTitles.size() - 1))) {
                break;
            }
            mCenterTextY += titleHeight + TEXT_LINE_SPACE;
        }
        //上面的线
        drawTopLine(canvas, titleHeight);

        drawBottomLine(canvas, titleHeight);
    }

    /**
     * 绘制上方的横线
     *
     * @param canvas
     * @param titleHeight
     */
    private void drawTopLine(Canvas canvas, float titleHeight) {
        float startX = 0;
        float startY = mCenterY - titleHeight / 2 - LINE_SPACE;
        float stopX = mCenterX * 2;
        canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
    }

    /**
     * 绘制下方的横线
     *
     * @param canvas
     * @param titleHeight
     */
    private void drawBottomLine(Canvas canvas, float titleHeight) {
        float startX = 0;
        float startY = mCenterY + titleHeight / 2 + LINE_SPACE;
        float stopX = mCenterX * 2;
        canvas.drawLine(startX, startY, stopX, startY, mLinePaint);
    }

    /**
     * 获取文本区域所在的矩形区域，帮助获取绘制范围宽高
     *
     * @param title
     * @return
     */
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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //和上次比划过的距离
            float v = event.getY() - y;
            y = event.getY();
            if (checkIsAllowOut(v)) {
                return true;
            }
            mCenterTextDefalutY += v;
            invalidate();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //防止滑出了第一条
            if (mCenterTextDefalutY - mCenterY >= 0) {
                //默认的初始文本中心和view中心相同或者大于就是在第一条
                mCenterTextDefalutY = mCenterY;
                invalidate();
                return true;
            }
            //防止滑出了最后一条
            if (mCenterTextY - mCenterY <= 0) {
                mCenterTextDefalutY = mCenterY - ((mTitles.size() - 1) * textTotalHeight);
                invalidate();
                return true;
            }


            mCenterTextDefalutY = mCenterY - (currentIndex * textTotalHeight);
            invalidate();


            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 检查是否允许超出顶部和底部
     *
     * @param v
     * @return
     */
    private boolean checkIsAllowOut(float v) {
        if (!disAllowOutTopOrBottom) {
            return false;
        }
        if (v > 0) {
            //已经显示的是第一条，禁止下滑
            if (mCenterTextDefalutY - mCenterY >= 0) {
                //默认的初始文本中心和view中心相同或者大于就是在第一条
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
                mCenterTextDefalutY = mCenterY - ((mTitles.size() - 1) * textTotalHeight);
                invalidate();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean performClick() {
        Log.e(TAG, "performClick");
        return super.performClick();
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    /**
     * 设置数据
     *
     * @param mTitles
     */
    public void setmTitles(List<String> mTitles) {
        this.mTitles = mTitles;
        invalidate();
    }


    /**
     * 选择回调接口
     */
    public interface OnItemSelectListener {
        /**
         * 返回当前的index
         *
         * @param index
         */
        void onItemSelect(int index);
    }
}
