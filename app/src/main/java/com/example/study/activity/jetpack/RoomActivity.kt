package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.jetpack.room.User
import com.example.study.jetpack.room.UserController
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            var intent = Intent(context, RoomActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var userController: UserController
    lateinit var userList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        insert.setOnClickListener(this)
        delete.setOnClickListener(this)
        query.setOnClickListener(this)
        update.setOnClickListener(this)

        userController = UserController.getUserControllerIns(applicationContext)

        if (UserController.userLiveData == null) {
            Log.e("DB", "null")
        }
        UserController.userLiveData?.observe(this, Observer {
            Log.e("DB", "observe")
            userList = it
            tv.text = it.toString()

        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.insert -> userController.insertUser(User(et.text.toString()))
            R.id.delete -> {
                userController.delete(userList[0])
            }
            R.id.query -> userController.queryUserAll()
            R.id.update -> {
                var user = userList[0]
                user.name = et.text.toString()
                userController.upDate(user)
            }

        }
    }
}
