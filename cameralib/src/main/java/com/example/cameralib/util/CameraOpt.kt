package com.example.cameralib.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.util.SparseIntArray
import android.view.Surface
import java.util.*
import kotlin.collections.ArrayList
import android.hardware.camera2.CameraManager as HardwareCamera2CameraManager


/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/28 16:59    <br>
 * Description: 相机操作实现类  <br>
 */
class CameraOpt(context: Context) : ICamera {


    var mContext: Context = context
    /*
    相机管理
     */
    lateinit var mCameraManager: HardwareCamera2CameraManager
    /**
     * 相机id
     */
    var mDefaultCameraId: String = "0"
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

    /**
     * 是否开闪光灯
     */
    var mDefaultFlashMode = CaptureRequest.FLASH_MODE_OFF

    /**
     * 是否处于打开状态
     */
    var isClosed = false

    @SuppressLint("ServiceCast", "Recycle")
    override fun openCamera(showSurface: Surface, width: Int, height: Int) {
        val handlerThread = HandlerThread("camera")
        handlerThread.start()
        cameraHandler = Handler(handlerThread.looper)

        mCameraManager = mContext.getSystemService(Context.CAMERA_SERVICE) as HardwareCamera2CameraManager

        mShowSurface = showSurface
        mWidth = width
        mHeight = height

        openCameraDevice()
    }

    override fun closeCamera() {
        mCameraDevice.close()
        isClosed=true
    }

    override fun changeCameraId() {
        mDefaultCameraId = if ("0" == mDefaultCameraId) {
            "1"
        } else {
            "0"
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

    @SuppressLint("MissingPermission")
    fun openCameraDevice() {
        val callback = object : CameraDevice.StateCallback() {
            override fun onOpened(camera: CameraDevice) {
                LogUtil.e("cameraTest", "onOpened")
                //相机打开成功
                mCameraDevice = camera

                //开始预览
                createPreviewSession()
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
        mCameraManager.openCamera(mDefaultCameraId, callback, cameraHandler)

        isClosed = false
    }

    private val ORIENTATION = SparseIntArray()

    init {
        ORIENTATION.append(Surface.ROTATION_0, 90)
        ORIENTATION.append(Surface.ROTATION_90, 0)
        ORIENTATION.append(Surface.ROTATION_180, 180)
        ORIENTATION.append(Surface.ROTATION_270, 270)
    }

    /**
     * 创建预览窗口
     */
    private fun createPreviewSession() {
        val requestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        requestBuilder.addTarget(mShowSurface)
        requestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
        val surfaceList = ArrayList<Surface>()
        surfaceList.add(mShowSurface)
        val callback = object : CameraCaptureSession.StateCallback() {
            override fun onConfigureFailed(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "onConfigureFailed")
            }

            override fun onConfigured(session: CameraCaptureSession) {
                LogUtil.e("cameraTest", "onConfigured")
                session.setRepeatingRequest(requestBuilder.build(), null, cameraHandler)
            }
        }
        mCameraDevice.createCaptureSession(surfaceList, callback, cameraHandler)
    }

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
//        requestBuilder.addTarget(mShowSurface)
        if (mContext is Activity) {

            val s = (mContext as Activity).windowManager.defaultDisplay.rotation
            requestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION.get(s))

        }
        requestBuilder.set(CaptureRequest.FLASH_MODE, mDefaultFlashMode)
        //添加处理数据的
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据
        val ir = ImageReader.newInstance(mWidth, mHeight, ImageFormat.JPEG, 2)
        ir.setOnImageAvailableListener({

            val image = it.acquireNextImage()
            if (image != null) {
                keepPhoto(image)

            }
        }, cameraHandler)
        requestBuilder.addTarget(ir.surface)
        val surfaceList = ArrayList<Surface>()
//        surfaceList.add(mShowSurface)
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
     * 是否在拍照
     */
    var isTakePhoto = false

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
        val fileUri = FileUtil.createFile("AudioTest", "photo.jpeg", mContext.contentResolver)
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, mContext.contentResolver.openOutputStream(fileUri))
        createPreviewSession()
        isTakePhoto = false

    }


}