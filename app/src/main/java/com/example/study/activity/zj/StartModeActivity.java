package com.example.study.activity.zj;

import android.os.Bundle;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.ZJActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/18 15:21    <br>
 * Description: TODO 请输入此类的功能   <br>
 */
public class StartModeActivity extends BaseActivity {

    @BindView(R.id.start)
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mode);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start)
    public void onViewClicked() {
        finish();
        ZJActivity.start(this,"mode重新跳转");
    }
}
