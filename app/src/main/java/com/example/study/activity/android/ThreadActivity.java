package com.example.study.activity.android;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.android.thread.AsyncTaskActivity;
import com.example.study.activity.android.thread.HandlerThreadActivity;
import com.example.study.activity.android.thread.IntentServiceActivity;
import com.example.study.activity.android.thread.ThreadLocalDemoActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends BaseActivity {

    @BindView(R.id.async)
    Button async;
    @BindView(R.id.handler_thread)
    Button handlerThread;
    @BindView(R.id.intent_service)
    Button intentService;

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadActivity.class);
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
        for (int i=0;i<10;i++){
            AsyncTask.THREAD_POOL_EXECUTOR.execute(()-> Log.e("thread","threadName"+Thread.currentThread().getName()));
        }
    }

    @OnClick({R.id.async, R.id.handler_thread, R.id.intent_service,R.id.thread_local})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.async:
                AsyncTaskActivity.start(this);
                break;
            case R.id.handler_thread:
                HandlerThreadActivity.start(this);
                break;
            case R.id.intent_service:
                IntentServiceActivity.start(this);
                break;
            case R.id.thread_local:
                ThreadLocalDemoActivity.Companion.start(this);
                break;
            default:
        }
    }
}
