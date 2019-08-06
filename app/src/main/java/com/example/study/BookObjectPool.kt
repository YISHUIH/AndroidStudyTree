package com.example.study

import androidx.core.util.Pools
import com.example.study.entity.Book

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/30 14:09    <br>
 * Description: 对象池   <br>
 */
class BookObjectPool(poolSize: Int) : Pools.Pool<Book> {
    var arrayOfBook: Array<Book?>
    var poolCount: Int = 0

    init {
        if (poolSize < 0) {
            throw Exception("对象池必大小须大于0")
        }
        arrayOfBook = arrayOfNulls(poolSize)
    }

    /**
     * 取出对象
     */
    override fun acquire(): Book? {
        if (poolCount > 0) {
            val lastIndex = poolCount - 1
            poolCount--
            val book = arrayOfBook[lastIndex]
            arrayOfBook[lastIndex] = null
            return book
        }
        return null
    }

    /**
     * 加入对象池
     */
    override fun release(instance: Book): Boolean {
        //判断是否已经在对象池里
        if (isInPool(instance)) {
            return false
        }
        //加入对象池
        if (poolCount < arrayOfBook.size) {
            arrayOfBook[poolCount]=instance
            poolCount++
            return true
        }

        return false

    }

    private fun isInPool(instance: Book): Boolean {
        for (book in arrayOfBook) {
            return book === instance
        }
        return false
    }
}