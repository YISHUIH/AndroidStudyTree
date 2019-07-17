package com.example.study.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 16:31    <br>
 * Description:  UserDataBase <br>
 */
@Database(version = 1, entities = [User::class])
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        var userDataBase: UserDataBase? = null
        @Synchronized
        fun getUserDataBaseInstace(context: Context): UserDataBase {
            return userDataBase
                    ?: Room.databaseBuilder(context, UserDataBase::class.java,"user_database").build()
        }
    }

}