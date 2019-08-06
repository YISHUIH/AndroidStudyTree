package com.example.study.activity.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import kotlinx.android.synthetic.main.activity_view_sub.*

class ViewStubActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewStubActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_sub)
        tv_content.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val s=view_stub?.inflate()
        val bt=s?.findViewById<View>(R.id.bt1)
        bt?.setOnClickListener(this)
        if (v?.id==R.id.bt1){
            tv_content.setText("ViewSub内容")
        }
    }
}
