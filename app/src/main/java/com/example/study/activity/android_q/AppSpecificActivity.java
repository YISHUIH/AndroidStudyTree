package com.example.study.activity.android_q;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.study.BaseActivity;
import com.example.study.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AppSpecificActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AppSpecificActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_specific);

        getSpecifiPath();


        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car);

        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(externalFilesDir.getAbsolutePath(), "test.png");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                }
            }).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void getSpecifiPath() {
        File[] externalMediaDirs = getExternalMediaDirs();
        Log.e(TAG, "externalMediaDirs:  " + externalMediaDirs.toString());

        File obbDir = getObbDir();
        Log.e(TAG, "obbDir:  " + obbDir.getAbsolutePath());
        File[] obbDirs = getObbDirs();
        showFilesPath(obbDirs, "obbDirs");

        File externalCacheDir = getExternalCacheDir();
        Log.e(TAG, "externalCacheDir:  " + externalCacheDir.getAbsolutePath());
        File[] externalCacheDirs = getExternalCacheDirs();
        showFilesPath(externalCacheDirs, "externalCacheDirs");

        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.e(TAG, "externalFilesDir:  " + externalFilesDir.getAbsolutePath());
        File[] externalFilesDirs = getExternalFilesDirs(Environment.DIRECTORY_PICTURES);
        showFilesPath(externalFilesDirs, "externalFilesDirs");
    }

    private void showFilesPath(File[] files, String praent) {
        for (File flie : files) {
            Log.e(TAG, praent + ":  " + flie.getAbsolutePath());
        }

    }
}
