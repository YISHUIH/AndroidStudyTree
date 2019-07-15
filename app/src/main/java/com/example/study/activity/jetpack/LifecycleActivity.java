package com.example.study.activity.jetpack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.jetpack.TestLifecycleObserver;

public class LifecycleActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, LifecycleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new TestLifecycleObserver());
        setContentView(R.layout.activity_lifecycle);
    }
}
