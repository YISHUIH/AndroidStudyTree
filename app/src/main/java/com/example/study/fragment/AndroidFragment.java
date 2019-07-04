package com.example.study.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.study.R;
import com.example.study.activity.AndroidForQActivity;
import com.example.study.activity.BitmapActivity;
import com.example.study.activity.ThreadActivity;
import com.example.study.activity.ZJActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/14 15:09    <br>
 * Description: 安卓部分   <br>
 */
public class AndroidFragment extends Fragment {

    @BindView(R.id.zj)
    Button zj;
    Unbinder unbinder;
    @BindView(R.id.thread)
    Button thread;

    public static AndroidFragment newInstance() {

        Bundle args = new Bundle();

        AndroidFragment fragment = new AndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.zj, R.id.thread,R.id.bitmap,R.id.android_Q})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zj:
                ZJActivity.start(getActivity(), "四大组件");
                break;
            case R.id.thread:
                ThreadActivity.start(getActivity());
                break;
            case R.id.bitmap:
                BitmapActivity.start(getActivity());
                break;
            case R.id.android_Q:
                AndroidForQActivity.start(getActivity());
                break;
            default:
        }
    }
}
