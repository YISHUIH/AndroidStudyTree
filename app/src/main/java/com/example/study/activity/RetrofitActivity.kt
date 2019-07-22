package com.example.study.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.api.RequestRepositorySearch
import com.example.study.entity.RepositorySearchBean
import com.example.study.jetpack.paging.RepositorySearchViewModel
import com.example.study.jetpack.room.User
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.android.synthetic.main.item_paging_layout.view.*

class RetrofitActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun start(context: Context) {
            var intent = Intent(context, RetrofitActivity::class.java)
            context.startActivity(intent)
        }
    }


    private lateinit var api: RequestRepositorySearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        request.setOnClickListener(this)

        rv.layoutManager = LinearLayoutManager(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.request -> {
            }
        }
    }

//    private fun doRequest() {
//        lateinit var call: Call<RepositorySearchBean>
//        call = api.request("View")
//        var callBack = object : Callback<RepositorySearchBean> {
//            override fun onResponse(call: Call<RepositorySearchBean>, response: Response<RepositorySearchBean>) {
//                Log.e("url", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<RepositorySearchBean>, t: Throwable) {
//                Log.e("url", t.toString())
//            }
//
//        }
//        call.enqueue(callBack)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
//
//
//    public fun getImageList(): Unit {
//        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        var cursor = contentResolver.query(uri, null, null, null, null)
//        cursor.moveToFirst()
//        for (name in cursor.columnNames) {
//            Log.e("columnName", "name:  $name")
//        }
//        while (cursor.moveToNext()) {
//            var data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
//            var name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
//            var type = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE))
//
//            Log.e("columnName", "path:  $data   name:  $name    type:  $type")
//        }
//        cursor.close()
//    }
//
//    public fun getFileList(): Unit {
//        val uri = MediaStore.Files.getContentUri("external")
//        val selection=MediaStore.Files.FileColumns.MIME_TYPE+"=?"
//        val selectionArgs= arrayOf("application/pdf")
//        var cursor = contentResolver.query(uri, null, selection, selectionArgs, null)
//        cursor.moveToFirst()
//        for (name in cursor.columnNames) {
//            Log.e("columnName", "name:  $name")
//        }
//        while (cursor.moveToNext()) {
//            var data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
//            var name = cursor.getString(cursor.getColumnIndex("name"))
//            var type = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE))
//
//            Log.e("columnName", "path:  $data   name:  $name    type:  $type")
//
//        }
//        cursor.close()
//    }
}
