package com.example.study.activity.android.zj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.study.BaseActivity;
import com.example.study.R;

public class BroadcastActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BroadcastActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
    }
}
