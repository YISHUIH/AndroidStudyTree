package com.example.study.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.study.jetpack.model.Book

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/12 14:20    <br>
 * Description: TestViewModle  <br>
 */
class TestViewModel : ViewModel() {


    val liveData = BookLiveData()

    fun doAction() {
        liveData.action(Book("菜菜", "大白菜"))
    }

    class BookLiveData : LiveData<Book>() {

        fun action(book: Book?) {
            //本线程中执行
            //value = book ?: Book("猴子", "无敌大狒狒")

            //将结果发送到主线程中
            postValue(book)
        }

    }

}