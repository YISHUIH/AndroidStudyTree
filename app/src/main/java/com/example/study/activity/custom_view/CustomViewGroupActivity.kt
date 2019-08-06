package com.example.study.activity.custom_view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import kotlinx.android.synthetic.main.activity_custom_view_group.*

class CustomViewGroupActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CustomViewGroupActivity::class.java)
            context.startActivity(intent)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_group)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
//        mViewGroup.setOnClickListener(this)


    }

    override fun onClick(v: View) {
//        mViewGroup.requestLayout()
//        mViewGroup.invalidate()
        val clickIndex = mViewGroup.indexOfChild(v)

        Log.e("click", "clickIndex :   $clickIndex")

        mViewGroup.detachViewFromParent(clickIndex)
        Log.e("click", "childCount :   ${mViewGroup.childCount}")

        mViewGroup.attachViewToParent(v, mViewGroup.centerIndx, v.layoutParams)
        mViewGroup.requestLayout()
        Log.e("click", "childCount2 :   ${mViewGroup.childCount}")


    }


}
