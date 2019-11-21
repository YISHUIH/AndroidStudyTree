package com.example.study.activity.android.thread

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_thread_local_demo.*

class ThreadLocalDemoActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ThreadLocalDemoActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_local_demo)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
