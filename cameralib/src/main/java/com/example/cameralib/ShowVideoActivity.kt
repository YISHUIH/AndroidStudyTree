package com.example.cameralib

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.example.cameralib.base.activity.BaseActivity

class ShowVideoActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ShowVideoActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_video)
    }
}
