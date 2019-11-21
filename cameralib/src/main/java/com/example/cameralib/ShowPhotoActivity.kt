package com.example.cameralib

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import com.example.cameralib.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_show_photo.*

class ShowPhotoActivity : BaseActivity() {
    companion object {
        fun start(context: Context, fileUri: Uri) {
            val starter = Intent(context, ShowPhotoActivity::class.java)
            starter.putExtra("fileUri", fileUri)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_show_photo)
        val fileUri = intent.getParcelableExtra("fileUri") as Uri

        mIv.setImageURI(fileUri)

        mTvCancel.setOnClickListener {
            finish()
        }
        mTvOk.setOnClickListener {
        }
    }
}
