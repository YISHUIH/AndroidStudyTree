package com.example.cameralib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cameralib.base.activity.BaseActivity

open class CameraActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CameraActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }
}
