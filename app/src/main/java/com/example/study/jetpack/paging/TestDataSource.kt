package com.example.study.jetpack.paging

import androidx.paging.PageKeyedDataSource
import com.example.study.jetpack.model.Book

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 14:39    <br>
 * Description: TestDataSource   <br>
 */
class TestDataSource : PageKeyedDataSource<Int, Book>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Book>) {

        callback.onResult(getData(), 1,2)
    }

    private fun getData(): List<Book> {
        var list=ArrayList<Book>()
        for (i in 0..10){
            list.add(Book("i","i*2"))
        }
        return list
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Book>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Book>) {

    }
}