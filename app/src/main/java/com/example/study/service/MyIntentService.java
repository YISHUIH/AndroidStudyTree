package com.example.study.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/28 15:33    <br>
 * Description: intentService   <br>
 */
public class MyIntentService extends IntentService {
    protected String TAG=getClass().getSimpleName()+"----";
    public MyIntentService() {
        this(null);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public MyIntentService(String name) {
        super(name);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("life",TAG+"onCreate--threadName: "+Thread.currentThread().getName());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("life",TAG+"onStart--threadName: "+Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("life",TAG+"onStartCommand--threadName: "+Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("life",TAG+"onDestroy--threadName: "+Thread.currentThread().getName());
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.i("life",TAG+"onBind--threadName: "+Thread.currentThread().getName());
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("life",TAG+"onUnbind--threadName: "+Thread.currentThread().getName());
        return super.onUnbind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("life",TAG+"onHandleIntent--threadName: "+Thread.currentThread().getName());

    }
}
