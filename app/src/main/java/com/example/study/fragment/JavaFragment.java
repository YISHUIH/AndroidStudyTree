package com.example.study.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.study.R;
import com.example.study.activity.java.AnnotationActivity;
import com.example.study.activity.java.SJSFActivity;
import com.example.study.util.LogUtil;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/14 15:09    <br>
 * Description: 安卓部分   <br>
 */
public class JavaFragment extends Fragment {

    public static JavaFragment newInstance() {

        Bundle args = new Bundle();

        JavaFragment fragment = new JavaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_java, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.bt_sf, R.id.bt_annotation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sf:
                SJSFActivity.start(getActivity());
                break;
            case R.id.bt_annotation:
//                AnnotationActivity.start(getActivity(),AnnotationActivity.class);

                FutureTask futureTask=new FutureTask<>(()->{
                    LogUtil.Companion.e("futureTask", "11111111111");
                }, "ssssssssssssssssss");

                AsyncTask.THREAD_POOL_EXECUTOR.execute(futureTask);

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (futureTask.isDone()){
                    try {
                        String s=(String) futureTask.get();
                        LogUtil.Companion.e("futureTask",s );
                        Button bt= (Button) view;
                        bt.setText(s);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;

            default:
        }
    }
}
