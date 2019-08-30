package com.example.cameralib

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.SurfaceTexture
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.view.WindowManager
import com.example.cameralib.base.activity.BaseActivity
import com.example.cameralib.custom.drawable.FlashStatusDrawable
import com.example.cameralib.util.CameraOpt
import com.example.cameralib.util.LogUtil
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CameraActivity::class.java)
            context.startActivity(starter)
        }
    }

    val mCameraOpt = CameraOpt(this@CameraActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_camera)
        onRequestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))

//        //拍照
//        mBtTakePhoto.setOnClickListener {
//            mCameraOpt.createTakePhotoSession()
//        }
        //取消
        mTvCancel.setOnClickListener {
            mCameraOpt.closeCamera()
            finish()
        }
        //切换摄像头
        mIvChangeId.setOnClickListener {
            mCameraOpt.changeCameraId()
        }
        //打开关闭闪光灯

        val flashStatusDrawable = FlashStatusDrawable()
        mIvFlash.setImageDrawable(flashStatusDrawable)
        mIvFlash.setOnClickListener {
            flashStatusDrawable.status = if (flashStatusDrawable.status == FlashStatusDrawable.STATUS_ON) {
                FlashStatusDrawable.STATUS_OFF
            } else {
                FlashStatusDrawable.STATUS_ON
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if (mCameraOpt.isClosed) {
            mCameraOpt.openCameraDevice()
        }
    }

    override fun onPermissionAllow(permission: String) {
        super.onPermissionAllow(permission)
        initTextureView()
    }

    override fun onPermissionDeny(permission: String) {
        super.onPermissionDeny(permission)
        finish()
    }

    private fun initTextureView() {

        mTextureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
                LogUtil.e("camera", "onSurfaceTextureSizeChanged")

            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
                LogUtil.e("camera", "onSurfaceTextureUpdated")
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
                LogUtil.e("camera", "onSurfaceTextureDestroyed")
                return true
            }

            override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
                LogUtil.e("camera", "onSurfaceTextureAvailable")
                mCameraOpt.openCamera(Surface(surface), width, height)
            }
        }
    }


    override fun onStop() {
        super.onStop()
        mCameraOpt.closeCamera()
    }
}
