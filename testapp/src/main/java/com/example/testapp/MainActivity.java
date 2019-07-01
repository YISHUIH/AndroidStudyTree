package com.example.testapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testapp.activity.AIDLServiceActivity;
import com.example.testapp.activity.ServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.service)
    Button service;
    @BindView(R.id.aidl)
    Button aidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.service, R.id.aidl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.service:
                ServiceActivity.start(this);
                break;
            case R.id.aidl:
                AIDLServiceActivity.start(this);
                break;

            default:
        }
    }
}

