package com.example.cameralib.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraMetadata
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import android.media.MediaRecorder
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.SparseIntArray
import android.view.Surface
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*
import kotlin.collections.ArrayList
import android.hardware.camera2.CameraManager as HardwareCamera2CameraManager


/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/28 16:59    <br>
 * Description: 相机操作实现类  <br>
 */
abstract class CameraOpt(context: Context) : ICamera, LifecycleObserver {
    companion object {
        //前置摄像头
        const val CAMERA_FACING_FRONT = "1"
        //后置摄像头
        const val CAMERA_FACING_BACK = "0"
    }

    var mContext: Context? = context
    /*
    相机管理
     */
    var mCameraManager: HardwareCamera2CameraManager? = null
    /**
     * 相机id
     */
    var mDefaultCameraId: String = CAMERA_FACING_BACK
    /**
     * 当前打开的相机
     */
    lateinit var mCameraDevice: CameraDevice

    var mWidth: Int = 0
    var mHeight: Int = 0
    /**
     * 预览的Surface
     */
    lateinit var mShowSurface: Surface
    /**
     * 工作线程
     */
    lateinit var cameraHandler: Handler
    lateinit var mainHandler: Handler
    lateinit var handlerThread: HandlerThread

    /**
     * 是否开闪光灯
     */
    var mDefaultFlashMode = CaptureRequest.FLASH_MODE_OFF


    @SuppressLint("ServiceCast", "Recycle")
    override fun openCamera(showSurface: Surface, width: Int, height: Int) {
        mCameraManager = mContext?.getSystemService(Context.CAMERA_SERVICE) as HardwareCamera2CameraManager

        mShowSurface = showSurface
        mWidth = width
        mHeight = height
        initHnalder()

    }

    override fun closeCamera() {
        cameraHandler.sendMessage(cameraHandler.obtainMessage(9))

    }

    override fun changeCameraId() {
        mDefaultCameraId = if (CAMERA_FACING_BACK == mDefaultCameraId) {
            CAMERA_FACING_FRONT
        } else {
            CAMERA_FACING_BACK
        }
        closeCamera()
        openCameraDevice()
    }


    override fun flashOffOrOn() {
        mDefaultFlashMode = if (CaptureRequest.FLASH_MODE_OFF == mDefaultFlashMode) {
            CaptureRequest.FLASH_MODE_TORCH
        } else {
            CaptureRequest.FLASH_MODE_OFF
        }
    }

    fun openCameraDevice() {

        val callback = object : CameraDevice.StateCallback() {
            override fun onOpened(camera: CameraDevice) {
                LogUtil.e("cameraTest", "onOpened")
                //相机打开成功
                mCameraDevice = camera

                //开始预览
                createVideoSession()
            }

            override fun onDisconnected(camera: CameraDevice) {
                LogUtil.e("cameraTest", "onDisconnected")
                //相机断开连接
                camera.close()

            }

            override fun onError(camera: CameraDevice, error: Int) {
                LogUtil.e("cameraTest", "onError")
                //打开相机出错
                camera.close()
            }

        }
        mCameraManager?.openCamera(mDefaultCameraId, callback, cameraHandler)

    }

    private fun initHnalder() {
        mainHandler=object :Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if (msg?.what==1){
                    val fileUri = mContext?.contentResolver?.let {
                        FileUtil.createFile("AudioTest", "audio.mp4", it)
                    }

                    onVideoSuccess(fileUri)
                }
            }
        }
        handlerThread = object : HandlerThread("camera") {
            override fun onLooperPrepared() {

            }
        }
        handlerThread.start()
        cameraHandler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if (msg?.what == 10) {
                    LogUtil.e("mediaRecorder", "start")
                    try {

                        mMediaRecorder.start()
                    } catch (e: IllegalStateException) {
                        LogUtil.e("IllegalStateException", "${e.message}   ${e.printStackTrace()}")
                    }
                }
                if (msg?.what == 11) {
                    LogUtil.e("mediaRecorder", "stop")
                    try {

                        mMediaRecorder.stop()

                        mainHandler.sendMessage(mainHandler.obtainMessage(1))
                    } catch (e: IllegalStateException) {
                        LogUtil.e("IllegalStateException", "${e.message}   ${e.printStackTrace()}")
                    }
                }

                if (msg?.what == 9) {
                    mMediaRecorder.release()
                    mCameraDevice.close()
                    handlerThread.quit()
                    mShowSurface.release()
                }
            }
        }

        openCameraDevice()

    }

    private val ORIENTATION = SparseIntArray()

    init {
        ORIENTATION.append(Surface.ROTATION_0, 90)
        ORIENTATION.append(Surface.ROTATION_90, 0)
        ORIENTATION.append(Surface.ROTATION_180, 180)
        ORIENTATION.append(Surface.ROTATION_270, 270)
    }

    /**
     * 是否在拍照
     */
    private var isTakePhoto = false

    /**
     * 创建拍照窗口
     */
    @Synchronized
    fun createTakePhotoSession() {
        if (isTakePhoto) {
            return
        }
        isTakePhoto = true
        val requestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
        if (mContext is Activity) {
            val s = (mContext as Activity).windowManager.defaultDisplay.rotation

            if (mDefaultCameraId == CAMERA_FACING_FRONT) {
                requestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION[s] + 180)
            } else {
                requestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION[s])
            }

        }
        requestBuilder.set(CaptureRequest.FLASH_MODE, mDefaultFlashMode)
        //添加处理数据的
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据
        val ir = ImageReader.newInstance(mHeight, mWidth, ImageFormat.JPEG, 2)
        ir.setOnImageAvailableListener({

            val image = it.acquireNextImage()
            if (image != null) {
                keepPhoto(image)
            }
        }, cameraHandler)
        requestBuilder.addTarget(ir.surface)
        val surfaceList = ArrayList<Surface>()
        surfaceList.add(ir.surface)

        val callback = object : CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "takePhoto   onConfigureFailed")
            }

            override fun onConfigured(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "takePhoto       onConfigured")
                try {
                    session.capture(requestBuilder.build(), null, cameraHandler)
                } catch (e: IllegalStateException) {
                    LogUtil.e("camera  IllegalStateException", "Session has been closed; further changes are illegal.")
                }

            }
        }
        mCameraDevice.createCaptureSession(surfaceList, callback, cameraHandler)
    }


    /**
     * 创建录像的窗口
     */
    fun createVideoSession() {
        val requestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD)
        val surfaceList = ArrayList<Surface>()
        setMediaRecorder(requestBuilder)
        requestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        requestBuilder.addTarget(mMediaRecorder.surface)
        surfaceList.add(mMediaRecorder.surface)
        requestBuilder.addTarget(mShowSurface)
        surfaceList.add(mShowSurface)

        val callback = object : CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "video   onConfigureFailed")
                session.close()
                closeCamera()
            }

            override fun onConfigured(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "video       onConfigured")

                try {
                    session.setRepeatingRequest(requestBuilder.build(), null, cameraHandler)
                } catch (e: IllegalStateException) {
                    LogUtil.e("camera  IllegalStateException", "Session has been closed; further changes are illegal.")
                }

            }
        }
        mCameraDevice.createCaptureSession(surfaceList, callback, cameraHandler)
        mMediaRecorder.setOnErrorListener { mr, what, extra ->
            LogUtil.e("error", "mr     what  $what   extra    $extra")
        }
    }

    lateinit var mMediaRecorder: MediaRecorder
    private fun setMediaRecorder(requestBuilder: CaptureRequest.Builder) {
        mMediaRecorder = MediaRecorder()
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        val fileUri = mContext?.contentResolver?.let {
            FileUtil.createFile("AudioTest", "audio.mp4", it)
        }
        mMediaRecorder.setOutputFile(mContext?.contentResolver?.openFileDescriptor(fileUri, "rw")!!.fileDescriptor)
        mMediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024)
        //每秒30帧
        mMediaRecorder.setVideoFrameRate(30)
        mMediaRecorder.setVideoSize(mHeight, mWidth)
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT)
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        if (mContext is Activity) {
            val s = (mContext as Activity).windowManager.defaultDisplay.rotation
            if (mDefaultCameraId == CAMERA_FACING_FRONT) {
                mMediaRecorder.setOrientationHint(ORIENTATION[s] + 180)
                requestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION[s] + 180)
            } else {
                mMediaRecorder.setOrientationHint(ORIENTATION[s])
                requestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION[s])
            }
        }
        mMediaRecorder.setPreviewDisplay(mShowSurface)
        mMediaRecorder.prepare()
    }

    /**
     * 保存拍到的照片
     */
    @Synchronized
    private fun keepPhoto(image: Image) {
        val buffer = image.planes[0].buffer
        val data = ByteArray(buffer.remaining())
        buffer.get(data)
        LogUtil.e("dataBuffer", Arrays.toString(data))
        image.close()
        val fileUri = mContext?.contentResolver?.let { FileUtil.createFile("AudioTest", "photo.jpeg", it) }
        var bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        if (mDefaultCameraId == CAMERA_FACING_FRONT) {
            //前置摄像头需要镜像水平旋转
            val matrix = Matrix()
            matrix.postScale(-1f, 1f)
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
        fileUri?.let {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, mContext?.contentResolver?.openOutputStream(it))
        }
        isTakePhoto = false
        //拍照成功
        if (fileUri != null) {
            onTakePhotoSuccess(fileUri)
        }

    }

    fun startVideo() {
        cameraHandler.sendMessage(cameraHandler.obtainMessage(10))
    }

    abstract fun onTakePhotoSuccess(fileUri: Uri)
    abstract fun onVideoSuccess(fileUri: Uri?)
    fun stopVideo() {
        cameraHandler.sendMessage(cameraHandler.obtainMessage(11))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        closeCamera()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (mWidth > 0) {
            initHnalder()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        handlerThread.quit()
        mCameraManager = null
        mContext = null
    }
}