package com.example.study.jetpack;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/10 15:50    <br>
 * Description: TestLifecycle   <br>
 */
public class TestLifecycleObserver implements LifecycleObserver {
    final String TAG = getClass().getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.i("life", TAG + "-----onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.i("life", TAG + "-----onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.i("life", TAG + "-----onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.i("life", TAG + "-----onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i("life", TAG + "-----onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i("life", TAG + "-----onDestroy");
    }


}
