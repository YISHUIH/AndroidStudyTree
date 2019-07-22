package com.example.study.jetpack.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.study.entity.RepositorySearchBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/18 16:36    <br>
 * Description: RepositorySearchDataScoure   <br>
 */
class RepositorySearchDataScoure : PageKeyedDataSource<Int, RepositorySearchBean.ItemsBean>() {

    var i:Int = 0
    private val re by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl("https://api.github.com")
                .build()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositorySearchBean.ItemsBean>) {
        Log.e("dataSource","loadInitial")
        callback.onResult(doRequest()?.items!!,i,i+1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositorySearchBean.ItemsBean>) {
        callback.onResult(doRequest()?.items!!,i)
        Log.e("dataSource","loadAfter")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositorySearchBean.ItemsBean>) {
        callback.onResult(doRequest()?.items!!,i)
        Log.e("dataSource","loadBefore")
    }

    private fun doRequest(): RepositorySearchBean? {

        var bean: RepositorySearchBean? = null
        var callBack = object : Callback<RepositorySearchBean> {
            override fun onResponse(call: Call<RepositorySearchBean>, response: Response<RepositorySearchBean>) {
                Log.e("url", response.body().toString())
                bean = response.body()
            }

            override fun onFailure(call: Call<RepositorySearchBean>, t: Throwable) {
                Log.e("url", t.toString())

            }

        }
        return bean

    }

}