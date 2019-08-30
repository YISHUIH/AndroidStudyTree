package com.example.cameralib.base.activity

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.cameralib.base.activity.BasePermissionActivity
import com.example.cameralib.util.LogUtil
import java.io.File

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/13 13:51    <br>
 * Description: 文件存储相关   <br>
 */
@SuppressLint("Registered")
open class BaseFileActivity : BasePermissionActivity() {

    var mParent: String? = null
    var mFileName: String? = null
    var mFileUri: Uri? = null
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
     * @param parent
     * @param mFileName
     */
    fun getStoragePermissions() {
        onRequestPermissions(arrayOf(android.Manifest.permission_group.STORAGE))
    }

    protected fun createFile(parent: String, fileName: String): Uri {
        val uri = MediaStore.Files.getContentUri("external")
        var fileUri: Uri?

        if (Build.VERSION.SDK_INT >= 29) {
            val values = ContentValues()
            values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, fileName)
            values.put(MediaStore.Files.FileColumns.TITLE, fileName)
            values.put(MediaStore.Files.FileColumns.MIME_TYPE, "NA")
            values.put("relative_path", "Download/$parent")
            val cursor = contentResolver.query(uri, arrayOf(MediaStore.Files.FileColumns.DISPLAY_NAME, MediaStore.Files.FileColumns._ID)
                    , "${MediaStore.Files.FileColumns.DISPLAY_NAME} =?", arrayOf(fileName), null)

            fileUri = if (!cursor!!.moveToNext()) {
                contentResolver.insert(uri, values)
            } else {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
                ContentUris.appendId(uri.buildUpon(), id).build()
            }
            cursor.close()
            return fileUri!!
        } else {
            val dirs = File("/storage/emulated/0", "/$parent/")
            if (!dirs.exists()) {
                LogUtil.e("ssssss", "111111111111")
                dirs.mkdirs()
            }

            val file = File(dirs, fileName)
            if (!file.exists()) {
                file.createNewFile()
                LogUtil.e("ssssss", "222222222222")
            }
            fileUri = Uri.fromFile(file)
            return fileUri
        }
    }
}