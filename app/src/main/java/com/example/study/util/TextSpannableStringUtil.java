package com.example.study.util;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

/**
 * 改变文本部分颜色
 */
public class TextSpannableStringUtil {

    /**
     * 获得搜索后变色的文本
     *<b/>
     * 改变第一个目标颜色
     * @param currentText 原始文本
     * @param colorText   要变色部分
     * @param colorSpan   文本风格  默认 ForegroundColorSpan(Color.GREEN);
     * @return 变色后的文本
     */
    public static SpannableString changeFirstTagColor(CharSequence currentText, String colorText, CharacterStyle colorSpan) {
        SpannableString spannableString = new SpannableString(currentText);
        if (colorSpan==null){
            colorSpan=new ForegroundColorSpan(Color.GREEN);
        }

        int start = 0;
        if (colorText != null && colorText.length() > 0) {
            start = ((String) currentText).indexOf(colorText);
        }

        //存在目标text变色
        if (start >= 0) {
            spannableString.setSpan(colorSpan, start, start + colorText.length(), Spanned.SPAN_COMPOSING);
        }

        return spannableString;

    }

    /**
     * 每次都会new一个新的ForegroundColorSpan
     * @param currentText
     * @param colorText
     * @param color
     * @return
     */
    public static SpannableString changeFirstTagColor(CharSequence currentText, String colorText, int color) {
        SpannableString spannableString = new SpannableString(currentText);
        CharacterStyle colorSpan=new ForegroundColorSpan(color);

        int start = 0;
        if (colorText != null && colorText.length() > 0) {
            start = ((String) currentText).indexOf(colorText);
        }

        //存在目标text变色
        if (start >= 0) {
            spannableString.setSpan(colorSpan, start, start + colorText.length(), Spanned.SPAN_COMPOSING);
        }

        return spannableString;

    }

    /**
     * 改变所以能找到的所有文本内容
     *
     * @param currentText
     * @param colorText
     * @param color
     * @return
     */
    public static SpannableStringBuilder changeAllTagColor(String currentText, String colorText, int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String[] split = currentText.split(colorText);
        for (String s : split) {
            if ((s.length() + builder.length()) == currentText.length()) {
                //如果已经到了最后，不需要在添加colorText
                //生成一个新的currentText
                builder.append(changeFirstTagColor(s, colorText, color));
            } else {
                //生成一个新的currentText
                builder.append(changeFirstTagColor(s + colorText, colorText, color));
            }

        }

        //对比少了多少个colorText
        int i = currentText.length() - builder.length();
        int i1 = i / colorText.length();
        //判断最后少了几个colorText，添加上
        for (int j = 0; j < i1; j++) {
            builder.append(changeFirstTagColor(colorText, colorText, color));
        }
        return builder;
    }

    /**
     * 改变整个文本的颜色
     *
     * @param currentText
     * @param colorSpan
     * @return
     */
    public static SpannableString changeAllTextColor(String currentText ,CharacterStyle colorSpan) {
        return changeFirstTagColor(currentText, currentText, colorSpan);
    }


}
