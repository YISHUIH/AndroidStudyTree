package com.example.testapp.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.testapp.BaseActivity;
import com.example.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends BaseActivity {

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;
    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.bt_2)
    Button bt2;
    /**
     * 得到Service创建的Messenger对象引用
     */
    private Messenger serviceMessenger;
    /**
     * Activity中的Messenger对象
     */
    private Messenger activityMessenger;
    /**
     * Activity中接收消息的handler
     */
    private ActivityHandler handler;


    public static void start(Context context) {
        Intent starter = new Intent(context, ServiceActivity.class);
        context.startActivity(starter);
    }

    boolean isBind = false;
    Intent intent;
    ServiceConnection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName("com.example.study", "com.example.study.service.MyService");
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                isBind = true;
                serviceMessenger = new Messenger(service);
                Log.i("life", "ServiceConnection-----onServiceConnected");
                Log.i("life", Thread.currentThread().getName());
                Log.i("life", "uid" + Binder.getCallingUid() + "pid" + Binder.getCallingPid());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBind = false;
                Log.i("life", "ServiceConnection----onServiceDisconnected");
            }
        };
        handler = new ActivityHandler();
        activityMessenger = new Messenger(handler);
    }

    @OnClick({R.id.start, R.id.stop, R.id.bindService, R.id.unbindService, R.id.bt_1, R.id.bt_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                if (isBind) {
                    unbindService(conn);
                    isBind = false;
                }
                break;
            case R.id.bt_1:
                Message message = Message.obtain();
                message.what = 5;
                message.replyTo = activityMessenger;
                try {
                    serviceMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_2:

                break;
            default:
        }
    }

    public class ActivityHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Log.i("life", "ActivityHandler----dispatchMessage");
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("life", "ActivityHandler----handleMessage");
            bt2.setText("service来消息啦");
            Log.i("life: ", "activity--name--------" + Thread.currentThread().getName());
        }
    }

}
