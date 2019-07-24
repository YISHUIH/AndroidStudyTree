package com.example.study.jetpack.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.study.entity.RepositorySearchBean


/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/22 16:03    <br>
 * Description: RepositorySearchDataScoureFactory   <br>
 */
class RepositorySearchDataScoureFactory(content:String?) : DataSource.Factory<Int, RepositorySearchBean.ItemsBean?>() {
    var content=content
    private val mSourceLiveData = MutableLiveData<RepositorySearchDataScoure>()
    override fun create(): DataSource<Int, RepositorySearchBean.ItemsBean?> {
        var repositorySearchDataScoure = RepositorySearchDataScoure(content)
        mSourceLiveData.postValue(repositorySearchDataScoure)
        return repositorySearchDataScoure
    }
}