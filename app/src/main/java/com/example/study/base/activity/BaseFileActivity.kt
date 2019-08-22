package com.example.study.base.activity

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/13 13:51    <br>
 * Description: 文件存储相关   <br>
 */
class BaseFileActivity :BasePermissionActivity() {
    lateinit var mBaseUri:Uri
    lateinit var mValues: ContentValues
    lateinit var mFileName: String
    /**
     * example for values
     * {
     * val values = ContentValues()
     * values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image")
     * values.put(MediaStore.Images.Media.DISPLAY_NAME, "Image.png")
     * values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
     * values.put(MediaStore.Images.Media.TITLE, "Image.png")
     * values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/test")
     *
     * }
     *
     * 保存文件的库
     * 1、可以动态权限申请
     * 2、适配Q
     * 3、支持模糊查询
     * 4、支持选择是否覆盖
     * @param baseUri
     * @param values
     */
    fun keepFile(baseUri: Uri, values: ContentValues,fileName:String) {
        mBaseUri=baseUri
        mValues=values
        mFileName=fileName

        //1、动态权限申请
        onRequestPermissions(arrayOf(android.Manifest.permission_group.STORAGE))

    }

    override fun onPermissionAllow(permission: String) {
        super.onPermissionAllow(permission)
        //2、适配Q
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//
//        }
        //查询文件是否已经存在
        val cursor=contentResolver.query(mBaseUri, arrayOf(MediaStore.MediaColumns._ID),"${MediaStore.MediaColumns.DISPLAY_NAME} =?", arrayOf(mFileName),null)
        if (cursor.moveToNext()){
            //文件已经存在

        }else{
            //文件不存在
            contentResolver.insert(mBaseUri,mValues)

        }
    }
}