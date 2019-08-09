package com.example.study.activity.android.custom_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.study.BaseActivity;
import com.example.study.R;

import butterknife.ButterKnife;

public class ViewTouchActivity extends BaseActivity {

//    @BindView(R.id.cv)
//    CustomView cv;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewTouchActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);
        ButterKnife.bind(this);

//        cv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("CustomView", "onTouch");
//                return false;
//            }
//        });
//
//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("CustomView", "onClick");
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
