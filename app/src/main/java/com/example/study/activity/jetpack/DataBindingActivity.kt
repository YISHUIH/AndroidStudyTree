package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.databinding.ActivityDataBindingBinding
import com.example.study.jetpack.model.Book
import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DataBindingActivity::class.java)
            context.startActivity(intent)
        }
    }

    //实体类对象
    lateinit var book: Book
    //布局构建的dataBanding对象
    lateinit var dataBanding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBanding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        book = Book("呐喊", "鲁迅")
        //设置对象
        dataBanding.book = book
        bt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //点击重新赋值对象
        dataBanding.book = Book("射雕英雄传", "金庸")
    }
}
