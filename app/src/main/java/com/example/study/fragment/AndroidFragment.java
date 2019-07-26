package com.example.study.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.study.R;
import com.example.study.activity.AndroidForQActivity;
import com.example.study.activity.BitmapActivity;
import com.example.study.activity.JetPackActivity;
import com.example.study.activity.KeepProcessAliveActivity;
import com.example.study.activity.RetrofitActivity;
import com.example.study.activity.SpannableStringActivity;
import com.example.study.activity.ThreadActivity;
import com.example.study.activity.ViewStudyActivity;
import com.example.study.activity.ZJActivity;
import com.example.study.activity.filemanager.FileManagerActivity;

import java.util.Objects;

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


    @OnClick({R.id.zj, R.id.thread, R.id.bitmap, R.id.android_Q, R.id.view, R.id.jet_pack, R.id.file_manager, R.id.retrofit, R.id.keep_process_alive, R.id.spannable_string})
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
            case R.id.view:
                ViewStudyActivity.start(getActivity());
                break;
            case R.id.jet_pack:
                JetPackActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.file_manager:
                FileManagerActivity.start(getActivity());
                break;
            case R.id.retrofit:
                RetrofitActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.keep_process_alive:
                KeepProcessAliveActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.spannable_string:
                SpannableStringActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            default:
        }
    }
}
