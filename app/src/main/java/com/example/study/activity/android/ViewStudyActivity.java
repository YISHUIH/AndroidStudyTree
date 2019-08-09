package com.example.study.activity.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.android.custom_view.CustomViewActivity;
import com.example.study.activity.android.custom_view.CustomViewGroupActivity;
import com.example.study.activity.android.custom_view.ViewTouchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewStudyActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewStudyActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.touch)
    Button touch;
    @BindView(R.id.view)
    Button view;
    @BindView(R.id.viewgroup)
    Button viewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_study);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.touch, R.id.view, R.id.viewgroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touch:
                ViewTouchActivity.start(this);
                break;
            case R.id.view:
                CustomViewActivity.start(this);
                break;
            case R.id.viewgroup:
                CustomViewGroupActivity.Companion.start(this);
                break;
            default:
        }
    }
}
