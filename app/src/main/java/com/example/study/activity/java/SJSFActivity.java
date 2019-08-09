package com.example.study.activity.java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.study.BaseActivity;
import com.example.study.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SJSFActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SJSFActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjsf);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ps, R.id.tree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ps:
                PXSFActivity.start(this);
                break;
            case R.id.tree:
                break;
            default:
        }
    }
}
