package com.example.study.jetpack.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.study.jetpack.model.Book

/**
 * Copyright , 2015-2019, <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 15:17    <br>
 * Description:  BookViewModel  <br>
 */
class BookViewModel : ViewModel() {

    val liveData = BookLiveData()

    class BookLiveData : LiveData<PagedList<Book>>() {

        fun action() {
            //本线程中执行
//            value = book ?: Book("猴子", "无敌大狒狒")

            //将结果发送到主线程中

//            postValue()
        }

    }


}