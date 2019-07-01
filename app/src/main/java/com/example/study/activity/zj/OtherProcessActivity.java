package com.example.study.activity.zj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.study.BaseActivity;
import com.example.study.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OtherProcessActivity extends BaseActivity {

    public static void start(Context context, String title) {
        Intent starter = new Intent(context, OtherProcessActivity.class);
        starter.putExtra("title", title);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_pross);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        if (intent.hasExtra("title")) {
            Log.i("title", "title===" + intent.getStringExtra("title"));
        }


        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        int age = sp.getInt("age", 0);
        Log.i("title", "name:" +name+"  age:"+age);

        try {
            File sdDir = Environment.getExternalStorageDirectory();
            FileInputStream fis=new FileInputStream(sdDir.getAbsolutePath()+"/fos.txt");
            int available = fis.available();
            byte[] b=new byte[available];
            fis.read(b);
            Log.i("title", "byte:" +new String(b));
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
