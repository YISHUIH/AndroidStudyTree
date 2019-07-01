package com.example.study.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.thread.AsyncTaskActivity;
import com.example.study.activity.thread.HandlerThreadActivity;
import com.example.study.activity.thread.IntentServiceActivity;

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
    }

    @OnClick({R.id.async, R.id.handler_thread, R.id.intent_service})
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
            default:
        }
    }
}
