package com.example.study.activity.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.android.android_q.AppSpecificActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidForQActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AndroidForQActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.specific)
    Button specific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_for_q);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.specific)
    public void onViewClicked() {
        AppSpecificActivity.start(this);

    }
}
