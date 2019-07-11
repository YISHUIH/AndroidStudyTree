package com.example.study.activity.jetpack;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.study.BaseActivity;
import com.example.study.R;

public class LifecycleActivity extends BaseActivity implements LifecycleObserver {

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecycleActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
    }
}
