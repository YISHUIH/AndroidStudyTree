package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/18 14:35    <br>
 * Description: TODO 请输入此类的功能   <br>
 */
public class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    public static void start(Context context, Class<? extends BaseActivity> c) {
        Intent starter = new Intent(context, c);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("life", TAG + "-----onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("life", TAG + "-----onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", TAG + "-----onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", TAG + "-----onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", TAG + "-----onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", TAG + "-----onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", TAG + "-----onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", TAG + "-----onDestroy");
    }
}
