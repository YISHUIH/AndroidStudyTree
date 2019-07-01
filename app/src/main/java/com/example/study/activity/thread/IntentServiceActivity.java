package com.example.study.activity.thread;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.service.MyIntentService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentServiceActivity extends BaseActivity {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;

    public static void start(Context context) {
        Intent starter = new Intent(context, IntentServiceActivity.class);
        context.startActivity(starter);
    }

    private Intent intent;
    private ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        ButterKnife.bind(this);
        intent=new Intent(this, MyIntentService.class);
        conn=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.start, R.id.stop, R.id.bindService, R.id.unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent,conn,Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                unbindService(conn);
                break;
                default:
        }
    }
}
