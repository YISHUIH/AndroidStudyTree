package com.example.study.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.activity.jetpack.DataBindingActivity
import com.example.study.activity.jetpack.LifecycleActivity
import com.example.study.activity.jetpack.LiveDataActivity
import com.example.study.activity.jetpack.ViewModelActivity
import kotlinx.android.synthetic.main.activity_jet_pack.*

class JetPackActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, JetPackActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack)
        bt_lifecycle.setOnClickListener(this)
        bt_live_data.setOnClickListener(this)
        bt_data_banding.setOnClickListener(this)
        bt_view_model.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        var v = view
//        v = null
        when (v?.id ?: R.id.bt_lifecycle) {
            R.id.bt_lifecycle -> LifecycleActivity.start(this)
            R.id.bt_live_data -> LiveDataActivity.start(this)
            R.id.bt_data_banding -> DataBindingActivity.start(this)
            R.id.bt_view_model -> ViewModelActivity.start(this)
        }
    }

}
