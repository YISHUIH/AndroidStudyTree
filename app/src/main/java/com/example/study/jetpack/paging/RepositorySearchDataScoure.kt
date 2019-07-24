package com.example.study.jetpack.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.study.api.RequestRepositorySearch
import com.example.study.entity.RepositorySearchBean
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
class RepositorySearchDataScoure(content: String?) : PageKeyedDataSource<Int, RepositorySearchBean.ItemsBean>() {

    var content=content

    var i: Int = 0
    private val re by lazy {
        var a = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                var request = chain.request()
                Log.e("url", request.url.toString())
                return chain.proceed(request)
            }
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(a)
                .build()

        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl("https://api.github.com")
                .client(okHttpClient)
                .build()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositorySearchBean.ItemsBean>) {
        Log.e("dataSource", "loadInitial")
        Log.e("dataSource", "${params.requestedLoadSize}")
        params.requestedLoadSize
        doRequest(callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositorySearchBean.ItemsBean>) {
//        callback.onResult(doRequest(callback)?.items!!,i)
        Log.e("dataSource", "loadAfter")
        Log.e("dataSource", "size:   ${params.requestedLoadSize}    key ${params.key}")
        doloadAfter(callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositorySearchBean.ItemsBean>) {
//        callback.onResult(doRequest(callback)?.items!!,i)
        Log.e("dataSource", "loadBefore")
    }

    private fun doRequest(callback: LoadInitialCallback<Int, RepositorySearchBean.ItemsBean>): RepositorySearchBean? {
        i = 0
        var bean: RepositorySearchBean? = null
        var callBack = object : Callback<RepositorySearchBean> {
            override fun onResponse(call: Call<RepositorySearchBean>, response: Response<RepositorySearchBean>) {
                Log.e("url", response.body().toString())
                bean = response.body()
                callback.onResult(bean?.items!!, i, i + 1)
            }

            override fun onFailure(call: Call<RepositorySearchBean>, t: Throwable) {
                Log.e("url", t.toString())

            }

        }
        val service = re.create(RequestRepositorySearch::class.java)
        val call = service.request(content?: "null", i)
        call.enqueue(callBack)
        return bean
    }

    private fun doloadAfter(callback: LoadCallback<Int, RepositorySearchBean.ItemsBean>): RepositorySearchBean? {
        i += 1
        var bean: RepositorySearchBean? = null
        var callBack = object : Callback<RepositorySearchBean> {
            override fun onResponse(call: Call<RepositorySearchBean>, response: Response<RepositorySearchBean>) {
                Log.e("url", response.body().toString())
                bean = response.body()
                if (bean?.items == null) {
                    return
                }
                callback.onResult(bean?.items!!, i + 1)
            }

            override fun onFailure(call: Call<RepositorySearchBean>, t: Throwable) {
                Log.e("url", t.toString())

            }

        }
        val service = re.create(RequestRepositorySearch::class.java)
        val call = service.request(content?: "null", i)
        call.enqueue(callBack)
        Log.e("url", "$i")
        return bean
    }

}