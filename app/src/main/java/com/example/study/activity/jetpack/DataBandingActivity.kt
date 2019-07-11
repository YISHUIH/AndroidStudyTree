package com.example.study.activity.jetpack

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.databinding.ActivityDataBandingBinding
import com.example.study.jetpack.model.Book
import kotlinx.android.synthetic.main.activity_data_banding.*

class DataBandingActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DataBandingActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var book: Book;
    lateinit var dataBanding: ActivityDataBandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBanding = DataBindingUtil.setContentView(this, R.layout.activity_data_banding)
        book = Book("呐喊", "鲁迅")
        dataBanding.book = book
        bt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        dataBanding.book = Book("射雕英雄传", "金庸")
    }
}
