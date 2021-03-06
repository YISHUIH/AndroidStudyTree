package com.example.study.jetpack.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.study.entity.RepositorySearchBean


/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/22 16:09    <br>
 * Description:   <br>
 */
class RepositorySearchViewModel : ViewModel() {
    var livaData = LivePagedListBuilder(RepositorySearchDataScoureFactory(null), PagedList.Config.Builder().setPrefetchDistance(2).build()).build()

    fun s(content:String): LiveData<PagedList<RepositorySearchBean.ItemsBean?>> {
        return LivePagedListBuilder(RepositorySearchDataScoureFactory(content), PagedList.Config.Builder().setPrefetchDistance(2).build()).build()
    }


}