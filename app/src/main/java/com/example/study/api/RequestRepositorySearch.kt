package com.example.study.api

import com.example.study.entity.RepositorySearchBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/17 15:41    <br>
 * Description: 搜索仓库   <br>
 */
interface RequestRepositorySearch {
    /**
     * search/repositories?q="ButterKnife"&page=1&per_page=1
     */
    @GET("search/repositories?q={@name}&page=1&per_page=10")
    fun request(@Query("name") name: String): Call<RepositorySearchBean>
}