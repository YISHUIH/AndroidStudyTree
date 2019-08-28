package com.example.study.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cameralib.CameraActivity;
import com.example.study.R;
import com.example.study.activity.android.AndroidForQActivity;
import com.example.study.activity.android.BitmapActivity;
import com.example.study.activity.android.CaughtExceptionActivity;
import com.example.study.activity.android.JetPackActivity;
import com.example.study.activity.android.KeepProcessAliveActivity;
import com.example.study.activity.android.RetrofitActivity;
import com.example.study.activity.android.SpannableStringActivity;
import com.example.study.activity.android.ThreadActivity;
import com.example.study.activity.android.ViewStudyActivity;
import com.example.study.activity.android.ZJActivity;
import com.example.study.activity.android.filemanager.FileManagerActivity;
import com.example.study.activity.android.html.HtmlActivity;
import com.example.study.activity.android.openGl.AudioDemoActivity;
import com.example.study.activity.android.openGl.OpenGlActivity;
import com.example.study.activity.android.ui.ViewStubActivity;

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


    @OnClick({R.id.zj, R.id.thread, R.id.bitmap, R.id.android_Q, R.id.view, R.id.jet_pack, R.id.file_manager, R.id.retrofit, R.id.keep_process_alive
            , R.id.spannable_string, R.id.view_sub,R.id.html,R.id.caughtException,R.id.OpenGL,R.id.audio,R.id.video})
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
            case R.id.view_sub:
                ViewStubActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.html:
                HtmlActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.caughtException:
                CaughtExceptionActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.OpenGL:
                OpenGlActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.audio:
                AudioDemoActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            case R.id.video:
                CameraActivity.Companion.start(Objects.requireNonNull(getActivity()));
                break;
            default:
        }
    }
}
