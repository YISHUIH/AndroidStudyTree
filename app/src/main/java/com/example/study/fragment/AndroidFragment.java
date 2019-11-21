package com.example.study.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.example.study.api.GetPrescriptionToken;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
            , R.id.spannable_string, R.id.view_sub, R.id.html, R.id.caughtException, R.id.OpenGL, R.id.audio, R.id.video})
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

    private void retrofit() {
        Interceptor interceptor = new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody body = request.body();

                MediaType mediaType = body.contentType();

                Log.e("url", request.url().toString());
                Log.e("url", "method" + request.method());
                Log.e("url", "mediaType---" + mediaType.toString());
                Log.e("url", "body---" + body.toString());
                Log.e("url", "length---" + body.contentLength());

                return chain.proceed(request);
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                //解析方法
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://cflz.nj12320.org/pbminterface/")
                .client(client)
                .build();
        GetPrescriptionToken getPrescriptionToken = retrofit.create(GetPrescriptionToken.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hosCode", "123254");
        jsonObject.addProperty("grantCode", "1232");
        jsonObject.addProperty("grantKey", "xxxx");
        String s = jsonObject.toString();
        Log.e("url", "body1---" + s);

        RequestBody requestBody = RequestBody.create(s, MediaType.parse("application/json;charset=utf-8"));
        Call<JsonObject> request = null;
        try {
            request = getPrescriptionToken.request(requestBody, requestBody.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.e("url", response.toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("url", "onFailure" + call.toString() + t.getMessage());
            }
        });
    }
}
