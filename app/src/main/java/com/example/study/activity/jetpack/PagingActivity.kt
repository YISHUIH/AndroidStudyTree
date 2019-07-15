package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R

class PagingActivity : BaseActivity() {
    companion object {
        fun start(context: Context){
            var intent=Intent(context,PagingActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

    }
}
