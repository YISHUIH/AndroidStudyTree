package com.example.study.activity.java;

import android.os.Bundle;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.util.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Hello(value = "111111111")
public class AnnotationActivity extends BaseActivity {

    @Hello(value = "222222")
    public String abc = "ss";

    @Hello(value = "33333333")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        try {
            Class clss = getClass();

            Hello clssAnnotation = (Hello) clss.getAnnotation(Hello.class);
            LogUtil.Companion.e("hello", clssAnnotation.value());

            Field field = clss.getField("abc");
            Hello hello = field.getAnnotation(Hello.class);
            LogUtil.Companion.e("hello", hello.value());

            Method method = clss.getDeclaredMethod("onCreate", Bundle.class);
            Hello methodAnnotation = method.getAnnotation(Hello.class);
            LogUtil.Companion.e("hello", methodAnnotation.value());


            long timeMillis = System.currentTimeMillis();

            Date date = new Date(timeMillis);
            date.getDay();
            date.getYear();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

