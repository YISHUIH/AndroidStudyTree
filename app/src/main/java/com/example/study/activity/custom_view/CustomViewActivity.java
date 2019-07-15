package com.example.study.activity.custom_view;

import androidx.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.R;
import com.example.study.view.CustomView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.cv)
    CustomView cv;
    @BindView(R.id.title)
    TextView title;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add("第 " + i + " 个标题");
        }
        cv.setmTitles(list);
        cv.setOnItemSelectListener(new CustomView.OnItemSelectListener() {
            @Override
            public void onItemSelect(int index) {
                title.setText(list.get(index));
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
        
    }
}
