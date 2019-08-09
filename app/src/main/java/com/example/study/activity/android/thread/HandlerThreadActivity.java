package com.example.study.activity.android.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HandlerThreadActivity extends BaseActivity {

    @BindView(R.id.send)
    Button send;
    @BindView(R.id.quitSafe)
    Button quitSafe;
    @BindView(R.id.quit)
    Button quit;
    @BindView(R.id.tv)
    TextView tv;
    private MyHandlerThread handlerThread;

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("name", "msg: uiHandler");
            tv.setText("子线程收到并返回了一个笑脸");
        }
    };

    public static void start(Context context) {
        Intent starter = new Intent(context, HandlerThreadActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.send, R.id.quitSafe, R.id.quit, R.id.start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                handlerThread = new MyHandlerThread("handlerThread");
                handlerThread.start();
                break;
            case R.id.send:
                if (handlerThread==null){
                    return;
                }
                if (handlerThread.getmHandler() == null) {
                    return;
                }
                Message obtain = Message.obtain();
                obtain.obj = uiHandler;
                handlerThread.getmHandler().sendMessage((obtain));
                break;
            case R.id.quitSafe:
                if (handlerThread==null){
                    return;
                }
                if (handlerThread.getmHandler() == null) {
                    return;
                }
                handlerThread.quitSafely();
                break;
            case R.id.quit:
                if (handlerThread==null){
                    return;
                }
                if (handlerThread.getmHandler() == null) {
                    return;
                }
                handlerThread.quit();
                break;
            default:
        }
    }


    public class MyHandlerThread extends HandlerThread {
        private Handler mHandler;

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            if (mHandler == null) {
                mHandler = new Handler(this.getLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.i("name", "msg: MyHandlerThread");

                        if (msg.obj instanceof Handler) {
                            Message obtain = Message.obtain();
                            ((Handler) msg.obj).sendMessage(obtain);
                        }
                    }
                };
            }
        }

        @Override
        public void run() {
            Log.i("name", "threadName1: " + Thread.currentThread().getName());
            super.run();
        }

        @Override
        public synchronized void start() {
            super.start();
            Log.i("thread", "start");
        }

        /**
         * {@link #start} 之后调用
         *
         * @return
         */
        public Handler getmHandler() {
            return mHandler;
        }
    }
}
