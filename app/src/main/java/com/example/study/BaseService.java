package com.example.study;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/19 9:28    <br>
 * Description: TODO 请输入此类的功能   <br>
 */
public abstract class BaseService extends Service {
    protected String TAG=getClass().getSimpleName()+"----";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("life",TAG+"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("life",TAG+"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("life",TAG+"onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("life",TAG+"onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i("life",TAG+"onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i("life",TAG+"onTrimMemory");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("life",TAG+"onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("life",TAG+"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("life",TAG+"onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i("life",TAG+"onTaskRemoved");
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
        Log.i("life",TAG+"dump");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.i("life",TAG+"attachBaseContext");
    }
}
