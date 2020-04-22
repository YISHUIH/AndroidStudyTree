package com.example.study.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

import androidx.annotation.NonNull;

/**
 * Date: 2019/8/6 9:21    <br>
 * Description: ClickableSpan   <br>
 */
public abstract class MyClickableSpan extends ClickableSpan {
    private final int mColor;

    public MyClickableSpan(int mColor) {
        this.mColor = mColor;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(mColor);
        ds.setUnderlineText(false);
    }

}
