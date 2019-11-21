package com.example.cameralib

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.media.ImageReader
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.WindowManager
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.VideoCapture
import androidx.camera.view.CameraView
import com.example.cameralib.base.activity.BaseActivity
import com.example.cameralib.custom.drawable.FlashStatusDrawable
import com.example.cameralib.custom.drawable.TakePhotoDrawable
import com.example.cameralib.util.FileUtil
import com.example.cameralib.util.LogUtil
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

class CameraActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CameraActivity::class.java)
            context.startActivity(starter)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.TRANSPARENT
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_camera)

        onRequestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))


    }

    override fun onPermissionAllow(permission: String) {
        super.onPermissionAllow(permission)
        mCv.bindToLifecycle(this)
        mCv.cameraLensFacing=CameraX.LensFacing.BACK
        mCv.captureMode = CameraView.CaptureMode.VIDEO
        initEvent()
    }

    override fun onPermissionDeny(permission: String) {
        super.onPermissionDeny(permission)
        finish()
    }

    /**
     * 点击事件
     */
    private fun initEvent() {
        //取消
        mTvCancel.setOnClickListener {
            finish()
        }
        //切换摄像头
        mIvChangeId.setOnClickListener {
            mCv.toggleCamera()
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

        //拍照录像
        photoOrVideo()
    }

    /**
     * 录像拍照按钮
     */
    private fun photoOrVideo() {
        //录像拍照按钮
        val takePhotoDrawable = object : TakePhotoDrawable() {
            override fun startVideo() {
                startVideoRecorder()
            }

            override fun stopVideo() {
                mCv.stopRecording()
            }
        }
        //注册生命周期
        lifecycle.addObserver(takePhotoDrawable)
        mIvTakePhoto.setImageDrawable(takePhotoDrawable)
        //拍照
        mIvTakePhoto.setOnClickListener {
            takePhotoDrawable.defaultStatus = TakePhotoDrawable.STATUS_TAKE_PHOTO
            val fileUri = FileUtil.createFile("AudioTest", "audio.png", contentResolver)
            val file = FileUtil.getFileFromUri(fileUri, contentResolver)
            mCv.captureMode = CameraView.CaptureMode.IMAGE

            LogUtil.e(TAG,"display.rotation  -----  ${ mCv.display.rotation}")
            mCv.takePicture(file, {
                it.run()
            }, object : ImageCapture.OnImageSavedListener {
                override fun onError(imageCaptureError: ImageCapture.ImageCaptureError, message: String, cause: Throwable?) {
                    LogUtil.e(TAG,"takePicture  -----  onError")
                }

                override fun onImageSaved(file: File) {
                    LogUtil.e(TAG,"takePicture  -----  onImageSaved")
//                    mCv.captureMode = CameraView.CaptureMode.VIDEO
                    ShowPhotoActivity.start(this@CameraActivity, fileUri)
                }
            })


        }
        //录像
        mIvTakePhoto.setOnLongClickListener {
            takePhotoDrawable.defaultStatus = TakePhotoDrawable.STATUS_TAKE_VIDEO
            true
        }

        mIvTakePhoto.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    LogUtil.e("s", "s")
                    if (takePhotoDrawable.defaultStatus == TakePhotoDrawable.STATUS_TAKE_VIDEO_START) {
                        //手指拿起结束录像
                        takePhotoDrawable.onStop()
                        true
                    }
                }
            }
            false
        }
    }

    fun startVideoRecorder() {
        val fileUri = FileUtil.createFile("AudioTest", "audio.mp4", contentResolver)
        val file = FileUtil.getFileFromUri(fileUri, contentResolver)
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        mCv.startRecording(file, {
            it.run()
        }, object : VideoCapture.OnVideoSavedListener {
            override fun onVideoSaved(file: File) {
                ShowVideoActivity.start(this@CameraActivity)
            }

            override fun onError(videoCaptureError: VideoCapture.VideoCaptureError, message: String, cause: Throwable?) {
            }
        })
    }
}


