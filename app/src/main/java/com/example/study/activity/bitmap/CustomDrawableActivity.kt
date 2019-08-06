package com.example.study.activity.bitmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.view.CustomDrawable
import kotlinx.android.synthetic.main.activity_custom_drawable.*

class CustomDrawableActivity : BaseActivity(), View.OnClickListener {


    companion object {
        fun start(context: Context) {
            var intent = Intent(context, CustomDrawableActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var customDrawable: CustomDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_drawable)
        customDrawable = CustomDrawable()
        cl.background = customDrawable
        cl.setOnClickListener(this)

        pb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               customDrawable.rang=progress.toDouble()
            }
        })

    }

    override fun onClick(v: View?) {
//        customDrawable.rang = 20.0


    }
}
