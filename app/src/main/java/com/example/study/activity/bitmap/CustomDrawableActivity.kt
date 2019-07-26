package com.example.study.activity.bitmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.view.CustomDrawable
import kotlinx.android.synthetic.main.activity_custom_drawable.*

class CustomDrawableActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            var intent= Intent(context,CustomDrawableActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_drawable)
        cl.background = CustomDrawable()

    }
}
