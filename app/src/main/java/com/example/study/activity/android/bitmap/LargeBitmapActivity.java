package com.example.study.activity.android.bitmap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.view.ShowLargeBitmap;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LargeBitmapActivity extends BaseActivity {

    @BindView(R.id.slb)
    ShowLargeBitmap slb;


    public static void start(Context context) {
        Intent starter = new Intent(context, LargeBitmapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_bitmap);
        ButterKnife.bind(this);
        try {
            InputStream is = getAssets().open("world.jpg");

            slb.setmInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClick({ R.id.bt_scale_big, R.id.bt_scale_small})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_scale_big:
                slb.scaleBitmap(1.5f, 1.5f);
                break;
            case R.id.bt_scale_small:
                slb.scaleBitmap(-1.0f, -1.0f);
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        slb.recycle();
    }
}
