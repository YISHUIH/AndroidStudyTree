package com.example.study.activity.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.android.zj.AIDLServiceActivity;
import com.example.study.activity.android.zj.BroadcastActivity;
import com.example.study.activity.android.zj.ContentProviderActivity;
import com.example.study.activity.android.zj.ServiceActivity;
import com.example.study.activity.android.zj.StartModeActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/14 15:09    <br>
 * Description: 四大组件   <br>
 */
public class ZJActivity extends BaseActivity {
    @BindView(R.id.ac)
    Button ac;
    @BindView(R.id.sv)
    Button sv;
    @BindView(R.id.br)
    Button br;
    @BindView(R.id.aidl)
    Button aidl;
    @BindView(R.id.cp)
    Button cp;

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, ZJActivity.class);
        starter.putExtra("title", title);
        context.startActivity(starter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zj);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.ac, R.id.sv, R.id.br, R.id.aidl,R.id.cp})
    public void onViewClicked(View view)  {
        switch (view.getId()) {
            case R.id.ac:
                StartModeActivity.start(this, StartModeActivity.class);
                break;
            case R.id.sv:
                ServiceActivity.start(this);
                break;
            case R.id.aidl:
                AIDLServiceActivity.start(this);

                break;
            case R.id.br:
                BroadcastActivity.start(this);
                break;
            case R.id.cp:
                ContentProviderActivity.start(this);
                break;
            default:
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getIntent();
        Log.e("title", intent.getStringExtra("title"));
    }
}
