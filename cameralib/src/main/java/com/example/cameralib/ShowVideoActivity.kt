package com.example.cameralib

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import com.example.cameralib.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_show_video.*

class ShowVideoActivity : BaseActivity() {

    companion object {
        fun start(context: Context,fileUri: Uri) {
            val intent = Intent(context, ShowVideoActivity::class.java)
            intent.putExtra("fileUri", fileUri)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_video)

        val fileUri = intent.getParcelableExtra("fileUri") as Uri


        mVideoView.setVideoURI(fileUri)
        mVideoView.start()

        mTvCancel.setOnClickListener {
            finish()
        }
        mTvOk.setOnClickListener {
        }
    }
}
