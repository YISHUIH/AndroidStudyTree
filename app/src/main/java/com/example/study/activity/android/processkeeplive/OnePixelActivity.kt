package com.example.study.activity.android.processkeeplive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.study.BaseActivity
import com.example.study.R

class OnePixelActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, OnePixelActivity::class.java)
            context.startActivity(intent)
        }
    }

    val receiver = CloseBroadcastReceiver(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_piexl)
        var attributes = window.attributes
        attributes.width = 1
        attributes.height = 1
        window.attributes = attributes

        window.attributes.width = 1
        window.attributes.height = 1

        val filter = IntentFilter()
        filter.addAction("close_one_pixel_ac")
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    class CloseBroadcastReceiver(oneAc: OnePixelActivity?) : BroadcastReceiver() {
        var ac = oneAc
        override fun onReceive(context: Context, intent: Intent?) {
            when (intent?.action) {
                "close_one_pixel_ac" -> {
                    if (ac != null) {
                        ac?.finish()
                    }
                }
            }
        }
    }
}
