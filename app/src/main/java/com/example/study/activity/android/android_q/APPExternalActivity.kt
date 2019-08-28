package com.example.study.activity.android.android_q

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.util.LogUtil
import kotlinx.android.synthetic.main.activity_appexternal.*


class APPExternalActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            var intent = Intent(context, APPExternalActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appexternal)

        onRequestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE))
        album.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(intent, 0)
        }

        photo.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 0)

        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val vNames = MediaStore.getExternalVolumeNames(this)
//            for (name in vNames) {
//
//                Log.e("name", "name  :  $name")
//                val uri = MediaStore.Images.Media.getContentUri(name)
//                Log.e("uri", "uri  :  $uri")
//            }
//
//
//
//            AsyncTask.THREAD_POOL_EXECUTOR.execute {
//                val values = ContentValues()
//                values.put(MediaStore.Images.Media.DESCRIPTION, "This is an image")
//                values.put(MediaStore.Images.Media.DISPLAY_NAME, "Image.png")
//                values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
//                values.put(MediaStore.Images.Media.TITLE, "Image.png")
//                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/test")
//                values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
//
//                val external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                val resolver = getContentResolver()
//                Log.e("external", "external :$external")
//                val cursor = resolver.query(external, arrayOf(MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media._ID),
//                        "${MediaStore.Images.Media.DISPLAY_NAME} =? ", arrayOf("IMG_20190813_023252.jpg"), null)
//                while (cursor.moveToNext()) {
//                    val name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
//
//                    val uri = ContentUris.appendId(external.buildUpon(),
//                            cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)))
//
//                    Log.e("ssssssssss", "sssssssssss  name:$name   uri: $uri")
//                    values.put(MediaStore.Images.Media.DISPLAY_NAME, "看我修改你.png")
//                    resolver.update(uri.build(), values, null, null)
//
//                }
//                cursor.close()
//
//
////                val insertUri = resolver.insert(external, values)
////                Log.e("insertUri", "insertUri: $insertUri")
////                var os: OutputStream? = null
////                try {
////                    if (insertUri != null) {
////                        os = resolver.openOutputStream(insertUri)
////                    }
////                    if (os != null) {
////                        val bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car)
////                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, os)
////                        // write what you want
////                    }
////                } catch (e: IOException) {
////                    Log.e("fail", "fail: " + e.cause)
////                } finally {
////                    try {
////                        if (os != null) {
////                            os!!.close()
////                        }
////                    } catch (e: IOException) {
////                        Log.e("fail in close", "fail in close: " + e.cause)
////                    }
////
////                }
//            }
//uri: uri : content://media/external/images/media/108164
//        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri = data?.data ?: return
        Log.e("uri", "uri : $uri")
//        val values = ContentValues()
//        values.put(MediaStore.MediaColumns.DISPLAY_NAME, "修改你个大头鬼.jpg")


//            contentResolver.update(uri, values, null, null)
        try {

//            val os = contentResolver.openInputStream(uri)
//            photo.background=BitmapDrawable.createFromStream(os,"s")
            val fd = contentResolver.openFileDescriptor(uri, "r")
            iv.setImageBitmap(BitmapFactory.decodeFileDescriptor(fd.fileDescriptor))

        }finally {

        }


    }

}
