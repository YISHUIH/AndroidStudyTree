package com.example.study.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.study.R;

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/6/14 15:09    <br>
 * Description: 安卓部分   <br>
 */
public class OtherFragment extends Fragment {

    public static OtherFragment newInstance() {

        Bundle args = new Bundle();

        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_other,container,false);
        return view;
    }
}
