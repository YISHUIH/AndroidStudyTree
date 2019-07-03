package com.example.study.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.activity.bitmap.LargeBitmapActivity;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BitmapActivity extends BaseActivity {

    @BindView(R.id.bt_ys)
    Button btYs;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.glide)
    Button glide;
    private Bitmap bitmap;

    public static void start(Context context) {
        Intent starter = new Intent(context, BitmapActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int rowBytes = bitmap.getRowBytes();
        int byteCount = bitmap.getByteCount();
        int allocationByteCount = bitmap.getAllocationByteCount();

        Log.e("bitmap", "width: " + width + "   height: " + height + "  rowBytes: " + rowBytes + "  byteCount: " + byteCount + "ssssssss" + rowBytes * height);
        Log.e("bitmap", "allocationByteCount: " + allocationByteCount);

        iv.setImageBitmap(bitmap);

    }

    private Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //设置true
        opts.inJustDecodeBounds = true;
        //不返回Bitmap对象，但是opts属性照样设置
        BitmapFactory.decodeResource(getResources(), R.drawable.car, opts);
        opts.inPreferredConfig = Bitmap.Config.RGB_565;

        //根据目标宽高和原始宽高计算比例
        int scaleWidth = opts.outWidth / newWidth;
        int scaleHeight = opts.outHeight / newHeight;
        //取出缩放比例小的一方
        //opts.inSampleSize = Math.min(scaleWidth,scaleHeight);
        opts.inSampleSize = 4;
        //比例设置好后，inJustDecodeBounds设置为false
        opts.inJustDecodeBounds = false;
        //得到缩放后的bitmap对象
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.car, opts);
        int byteCount = bitmap1.getByteCount();
        int rowBytes = bitmap1.getRowBytes();
        int allocationByteCount = bitmap1.getAllocationByteCount();
        int height = bitmap1.getHeight();
        int width = bitmap1.getWidth();

        Log.e("bitmap", "width: " + width + "   height: " + height + "  rowBytes: " + rowBytes + "  byteCount: " + byteCount + "ssssssss" + rowBytes * height);

        Log.e("bitmap", "allocationByteCount: " + allocationByteCount);

        return bitmap1;
    }


    @OnClick({R.id.bt_ys, R.id.glide,R.id.largeBitmap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_ys:
                iv.setImageBitmap(scaleBitmap(bitmap, 100, 100));
                break;
            case R.id.glide:
                Glide.with(iv).load(R.drawable.car).into(iv);
                break;
            case R.id.largeBitmap:
                LargeBitmapActivity.start(this);
                break;
            default:
        }
    }
}
