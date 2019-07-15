package com.example.study.jetpack.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 16:31    <br>
 * Description:  UserDataBase <br>
 */
@Database(version = 1, entities = [User::class])
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

}