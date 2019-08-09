package com.example.study.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.study.R;
import com.example.study.activity.java.SJSFActivity;

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

    @OnClick(R.id.bt_sf)
    public void onViewClicked() {
        SJSFActivity.start(getActivity());

    }
}
