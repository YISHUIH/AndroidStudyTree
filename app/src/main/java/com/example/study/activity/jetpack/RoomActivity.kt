package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.jetpack.room.User
import com.example.study.jetpack.room.UserDao
import com.example.study.jetpack.room.UserDataBase
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context): Unit {
            var intent= Intent(context,RoomActivity::class.java)
            context.startActivity(intent)
        }
    }
    lateinit var userDataBase: UserDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        insert.setOnClickListener(this)

      userDataBase=  UserDao.getUserDataBase(applicationContext)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.insert->userDataBase.getUserDao().insertAll(User(et.text.toString()))
            R.id.delete->userDataBase.getUserDao().delete(User(et.text.toString()))
            R.id.query->tv.text=userDataBase.getUserDao().all.toString()
        }
    }
}
