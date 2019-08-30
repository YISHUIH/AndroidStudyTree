package com.example.cameralib.util

import android.content.Context
import android.view.Surface

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/28 16:52    <br>
 * Description: 相机操作接口  <br>
 */
interface ICamera {
    /**
     * 打开相机
     * @param Surface 预览相机的Surface，
     */
    fun openCamera(showSurface: Surface,  width: Int, height: Int)

    /**
     * 关闭相机
     */
    fun closeCamera()

    /**
     * 切换摄像头
     */
    fun changeCameraId()

    /**
     * 打开关闭闪光灯
     */
    fun flashOffOrOn()
}