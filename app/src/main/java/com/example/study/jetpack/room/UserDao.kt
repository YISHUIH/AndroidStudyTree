package com.example.study.jetpack.room

import androidx.paging.DataSource
import androidx.room.*


/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 16:28    <br>
 * Description: userDao  <br>
 */
@Dao
interface UserDao {
    @get:Query("Select * from user")
    val all: List<User>

    @Query("Select * from user Where id In (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(vararg users: User)

    @Update
    fun update(vararg users: User)

    @Query("update user set name =:name1 where id=:id")
    fun updateCustom(name1: String, id: Int)

    @Query("Select * from user order by id")
    fun getAllUser():DataSource.Factory<Int,User>

}