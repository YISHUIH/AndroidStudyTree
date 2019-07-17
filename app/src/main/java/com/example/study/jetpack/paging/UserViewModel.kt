package com.example.study.jetpack.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.study.jetpack.room.UserController

/**
 * Copyright , 2015-2019, <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 15:17    <br>
 * Description:  BookViewModel  <br>
 */
class UserViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        private const val PAGE_SIZE = 3

        private const val ENABLE_PLACEHOLDERS = false
    }

    val userDao = UserController.userDataBase.getUserDao()
    val allUser = LivePagedListBuilder(userDao.getAllUser(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
            .setInitialLoadSizeHint(3)
            .setPrefetchDistance(2)
            .build()).build()


}