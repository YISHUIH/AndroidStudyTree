package com.example.study.activity.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.android.android_q.APPExternalActivity;
import com.example.study.activity.android.android_q.AppSpecificActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidForQActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AndroidForQActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_for_q);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.specific, R.id.out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.specific:
                AppSpecificActivity.start(this);
                break;
            case R.id.out:
                APPExternalActivity.Companion.start(this);
                break;
            default:
        }
    }
}
