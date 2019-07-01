package com.example.study.activity.thread;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.study.R;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTaskActivity extends AppCompatActivity {


    public static void start(Context context) {
        Intent starter = new Intent(context, AsyncTaskActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        for (int i=0;i<5;i++){
            new MyAsyncTask().execute(new ArrayList());

        }
    }


    public class MyAsyncTask extends AsyncTask<List, Integer, String> {
        /**
         * 在doInBackground之前调用。运行在UI线程中
         * 一般做些初始化操作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 具体处理任务的方法，在子线程中运行
         *
         * @param list
         * @return 任务处理完的时候返回的结果
         */
        @Override
        protected String doInBackground(List... list) {
            int length = list.length;
            for (int i = 0; i < length; i++) {
                List list1 = list[i];
                list1.add("你好");
                //把当前进度转到onProgressUpdate，UI线程中
                publishProgress(list1.size());
            }

            for (int i=0;i<5;i++){
                Log.i("task", "i:  " +i+"  threadName:  " +Thread.currentThread().getName());
            }

            return "完成喽";
        }

        /**
         * 调用publishProgress之后会调用此方法并。
         * 内部是利用Handler进行消息处理，实现回调，并传入参数
         * 运行在UI线程中
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int length = values.length;
            for (int i = 0; i < length; i++) {
//                Log.i("task", "values:  " + values[i] + "   i:" + i);
            }
        }

        /**
         * 不调用cancel方法并且doInBackground执行完成会回调
         * 回调的完成也是通过handler，并传入参数
         *
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        /**
         * 调用cancel方法并且doInBackground执行完成会回调
         * 回调的完成也是通过handler，并传入参数
         *
         * @param s
         */
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }
}
