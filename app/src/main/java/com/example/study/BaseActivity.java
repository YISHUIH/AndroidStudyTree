package com.example.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.study.base.activity.BaseFileActivity;
import com.example.study.base.activity.BasePermissionActivity;
import com.example.study.util.LogUtil;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/18 14:35    <br>
 * Description: BaseActivity  <br>
 */
public class BaseActivity extends BaseFileActivity {
    protected String TAG = getClass().getSimpleName();


    public static void start(Context context, Class<? extends BaseActivity> c) {
        Intent starter = new Intent(context, c);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.Companion.i("life", TAG + "-----onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.Companion.i("life", TAG + "-----onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.Companion.i("life", TAG + "-----onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.Companion.i("life", TAG + "-----onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.Companion.i("life", TAG + "-----onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.Companion.i("life", TAG + "-----onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.Companion.i("life", TAG + "-----onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.Companion.i("life", TAG + "-----onDestroy");

    }

}
