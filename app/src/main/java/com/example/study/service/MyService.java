package com.example.study.service;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.study.BaseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/19 9:26    <br>
 * Description: Service   <br>
 */
public class MyService extends BaseService {
    private MyHandler handler;
    private Messenger messenger;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new MyHandler();
        messenger = new Messenger(handler);
        Thread.currentThread().setName("service---thread");
        Log.i("life: ", "service--name--------" + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public MyHandler getHandler() {
        return handler;
    }


    public class MyBind extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }


    public static class MyHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Log.i("life", "MyHandler----dispatchMessage");


        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("life", "MyHandler----handleMessage");
            Messenger messenger=msg.replyTo;
            try {
                msg=Message.obtain();
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
