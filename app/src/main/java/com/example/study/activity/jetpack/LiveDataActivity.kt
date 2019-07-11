package com.example.study.activity.jetpack

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.jetpack.TestLiveData
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, LiveDataActivity::class.java)
            context.startActivity(starter)
        }
    }

    private val testLiveData: TestLiveData by lazy {
        TestLiveData(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        testLiveData.observe(this, Observer<Boolean> {
            var message = if (it!!) "网络连接成功" else "网络连接失败"

            tv.text = message
        })
    }
}
