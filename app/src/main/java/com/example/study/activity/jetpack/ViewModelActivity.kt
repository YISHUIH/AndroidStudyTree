package com.example.study.activity.jetpack

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.study.R
import com.example.study.jetpack.TestViewModel
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ViewModelActivity::class.java)
            context.startActivity(intent)

        }
    }

    lateinit var viewModel: TestViewModel
    lateinit var dataBinding: com.example.study.databinding.ActivityViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model)

        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)

//        val  viewModel: TestViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)

        viewModel.liveData.observe(this, Observer {
            dataBinding.book = it
        })

        bt.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        viewModel.doAction()
    }
}
