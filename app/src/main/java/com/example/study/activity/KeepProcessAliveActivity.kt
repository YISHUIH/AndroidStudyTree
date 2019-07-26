package com.example.study.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.study.R
import com.example.study.activity.processkeeplive.AvoidKilledActivity
import com.example.study.activity.processkeeplive.KilledReliveActivity
import kotlinx.android.synthetic.main.activity_keep_process_alive.*

class KeepProcessAliveActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun start(context: Context){
            val intent=Intent(context,KeepProcessAliveActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keep_process_alive)
        avoid_killed.setOnClickListener(this)
        killed_relive.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.avoid_killed->AvoidKilledActivity.start(this)
            R.id.killed_relive->KilledReliveActivity.start(this)
        }
    }
}
