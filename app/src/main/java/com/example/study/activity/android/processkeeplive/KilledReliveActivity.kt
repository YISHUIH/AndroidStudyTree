package com.example.study.activity.android.processkeeplive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R

class KilledReliveActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, KilledReliveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_killed_relive)
    }
}
