package com.example.study.jetpack.room

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/16 11:15    <br>
 * Description: 数据库的操作类  <br>
 */
class UserController {


    companion object {

        //本类对象
        var userController: UserController? = null
        lateinit var userDataBase: UserDataBase
        var userLiveData: UserLiveData? = null

        @Synchronized
        fun getUserControllerIns(context: Context): UserController {
            userDataBase = UserDataBase.getUserDataBaseInstace(context)
            userLiveData = UserLiveData.getUserLiveDataIns()

            return userController ?: UserController()
        }
    }


    class UserLiveData : LiveData<ArrayList<User>>() {
        companion object {
            var userLiveData: UserLiveData? = null
            @Synchronized
            fun getUserLiveDataIns(): UserLiveData {
                userLiveData = UserLiveData()

                return userLiveData ?: UserLiveData()
            }
        }

        fun addAll(users: ArrayList<User>) {
            value = users
        }

        fun add(user: User) {
            var list = ArrayList<User>()
            list.addAll(value ?: ArrayList())
            list.add(user)
            value = list
        }
    }

    /**
     * 插入
     */
    fun insertUser(vararg users: User) {
        for (user in users) {
            InsertAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, user)
        }
    }

    /**
     * 查询
     */
    fun queryUserAll() {
        QueryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, User())
    }

    /**
     * 删除
     */
    fun delete(user: User) {
        DeleteAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, user)
    }

    fun upDate(user: User) {
        UpDateAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user)
    }


    class InsertAsyncTask : AsyncTask<User, Int, User>() {
        override fun doInBackground(vararg params: User): User {
            Log.e("DB", "doInBackground")
            UserController.userDataBase.getUserDao().insertAll(params[0])
            return params[0]
        }

        override fun onPostExecute(result: User) {
            super.onPostExecute(result)
            UserController.userLiveData?.add(result)
            Log.e("DB", "onPostExecute")
        }

        override fun onCancelled(result: User) {
            super.onCancelled(result)
            UserController.userLiveData?.add(result)
            Log.e("DB", "onCancelled")
        }
    }


    class QueryAsyncTask : AsyncTask<User, Int, ArrayList<User>>() {
        override fun doInBackground(vararg params: User): ArrayList<User> {
            Log.e("DB", "doInBackground")
            var list = ArrayList<User>()
            var l = UserController.userDataBase.getUserDao().all
            list.addAll(l)
            return list
        }

        override fun onPostExecute(result: ArrayList<User>) {
            super.onPostExecute(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onPostExecute")
        }

        override fun onCancelled(result: ArrayList<User>) {
            super.onCancelled(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onCancelled")
        }
    }


    class DeleteAsyncTask : AsyncTask<User, Int, ArrayList<User>>() {
        override fun doInBackground(vararg params: User): ArrayList<User> {
            Log.e("DB", "doInBackground")
            UserController.userDataBase.getUserDao().delete(params[0])
            var list = ArrayList<User>()
            var l = UserController.userDataBase.getUserDao().all
            list.addAll(l)
            return list
        }

        override fun onPostExecute(result: ArrayList<User>) {
            super.onPostExecute(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onPostExecute")
        }

        override fun onCancelled(result: ArrayList<User>) {
            super.onCancelled(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onCancelled")
        }
    }

    class UpDateAsyncTask : AsyncTask<User, Int, ArrayList<User>>() {
        override fun doInBackground(vararg params: User): ArrayList<User> {
            Log.e("DB", "doInBackground")
            UserController.userDataBase.getUserDao().update(params[0])
            var list = ArrayList<User>()
            var l = UserController.userDataBase.getUserDao().all
            list.addAll(l)
            return list
        }

        override fun onPostExecute(result: ArrayList<User>) {
            super.onPostExecute(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onPostExecute")
        }

        override fun onCancelled(result: ArrayList<User>) {
            super.onCancelled(result)
            UserController.userLiveData?.addAll(result)
            Log.e("DB", "onCancelled")
        }
    }
}