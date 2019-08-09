package com.example.study.activity.java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.study.BaseActivity;
import com.example.study.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PXSFActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PXSFActivity.class);
        context.startActivity(starter);
    }

    int[] arry = {7, 3, 5, 4, 6, 8, 10};
    @BindView(R.id.arry)
    TextView tv;
    @BindView(R.id.arryAfter)
    TextView tvAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pxsf);
        ButterKnife.bind(this);
        tv.setText(Arrays.toString(arry));


    }


    @OnClick({R.id.mao_pao, R.id.xuan_ze, R.id.cha_ru, R.id.kuai_su, R.id.xi_er, R.id.bing_gui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mao_pao:
                tvAfter.setText(maopao());
                break;
            case R.id.xuan_ze:
                tvAfter.setText(xuanze());
                break;
            case R.id.cha_ru:
                tvAfter.setText(charu(19, 2, 0));
                break;
            case R.id.kuai_su:
                quickSort(arry, 0, arry.length - 1);
                tvAfter.setText(Arrays.toString(arry));
                break;
            case R.id.xi_er:
                tvAfter.setText(xuanze());
                break;
            case R.id.bing_gui:
                tvAfter.setText(xuanze());
                break;
            default:
        }
    }

    /**
     * 快速排序，已某个元素为参考值，大于它的放在右边，小于它的放在左边
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        //先看右边，依次往左递减
        while (temp <= arr[j] && i < j) {
            j--;
        }
        //再看左边，依次往右递增
        while (temp >= arr[i] && i < j) {
            i++;
        }
        //如果满足条件则交换
        if (i < j) {
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }


        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    /**
     * 原数组中插入数据。然后排序
     *
     * @param a 要插入内容
     * @return 排序后数字数据
     */
    private String charu(int... a) {
        arry = Arrays.copyOf(arry, arry.length + a.length);
        for (int j = 0; j < a.length; j++) {
            arry[arry.length - 1 - j] = a[j];
        }
        xuanze();


        return Arrays.toString(arry);
    }

    /**
     * 假设当前的下标元素最小，一次和后面比较，并标记出最小的小标。比较完成后和当前交换数据
     *
     * @return 排序后数字数据
     */
    private String xuanze() {
        for (int i = 0; i < arry.length; i++) {
            //假设当前的下标就是最小的元素下标
            int minIndex = i;
            for (int j = i + 1; j < arry.length; j++) {
                //和每一个进行比较
                if (arry[minIndex] > arry[j]) {
                    //找出最小的下标
                    minIndex = j;
                }
            }
            //交换数据
            int temp = arry[i];
            arry[i] = arry[minIndex];
            arry[minIndex] = temp;
        }
        return Arrays.toString(arry);
    }


    /**
     * 冒泡排序，每次把最大的放在最后
     *
     * @return 排序后的结果
     */
    private String maopao() {
        for (int i = 0; i < arry.length - 1; i++) {
            for (int j = i + 1; j < arry.length; j++) {
                if (arry[i] > arry[j]) {
                    int temp = arry[i];
                    arry[i] = arry[j];
                    arry[j] = temp;
                }
            }
        }
        return Arrays.toString(arry);
    }
}
