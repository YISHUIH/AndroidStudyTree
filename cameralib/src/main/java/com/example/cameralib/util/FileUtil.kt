package com.example.cameralib.util

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.File

/**
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/29 14:03    <br>
 * Description: 文件工具类  <br>
 */
 class  FileUtil {
    companion object {
        fun createFile(parent: String, fileName: String, contentResolver: ContentResolver): Uri {
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


        fun getFileFromUri(fileUri: Uri, contentResolver: ContentResolver): File {
            var filePath: String? = ""
            if (Build.VERSION.SDK_INT >= 29) {
                val cursor = contentResolver.query(fileUri, null, null, null, null)
                if (cursor?.moveToFirst() == true) {
                    filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA))
                } else {
                    LogUtil.e("ShowVideoActivity", "move to first failed")
                }
                cursor?.close()
            } else {
                filePath = fileUri.path
            }

            return File(filePath)
        }
    }


}