package com.example.study.activity.android.processkeeplive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.study.BaseActivity
import com.example.study.R

class AvoidKilledActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AvoidKilledActivity::class.java)
            context.startActivity(intent)
        }
    }

    var receiver = ScreenBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avoid_killed)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    class ScreenBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_SCREEN_ON -> Log.e("screen", "ACTION_SCREEN_ON")
                Intent.ACTION_SCREEN_OFF -> {
                    Log.e("screen", "ACTION_SCREEN_OFF")
                    OnePixelActivity.start(context)
                }
                Intent.ACTION_USER_PRESENT -> {
                    Log.e("screen", "ACTION_USER_PRESENT")
                    val l = LocalBroadcastManager.getInstance(context)

                    val int = Intent()
                    int.action = "close_one_pixel_ac"
                    l.sendBroadcast(int)
                }
            }
        }
    }
}
