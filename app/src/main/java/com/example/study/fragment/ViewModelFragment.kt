package com.example.study.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.study.R
import com.example.study.jetpack.TestViewModel
import kotlinx.android.synthetic.main.view_model_fragment.*

class ViewModelFragment : Fragment() {

    companion object {
        fun newInstance() = ViewModelFragment()
    }

     lateinit var viewModel: TestViewModel

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fas=activity as FragmentActivity
        //获得ac的ViewModel，ViewModel都被存在一个map中，key唯一，所以ViewModel唯一
        viewModel = ViewModelProviders.of(fas).get(TestViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            tv.text = "书名： ${it?.name}  作者:  ${it?.author}"
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_model_fragment, container, false)
    }


}
