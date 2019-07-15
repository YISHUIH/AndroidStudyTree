package com.example.study.jetpack

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log

/**
 * Copyright , 2015-2019, <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/10 17:12    <br>
 * Description: TestLiveData   <br>
 */
class TestLiveData(val context: Context) : LiveData<Boolean>() {
    val tag="TestLiveData"

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var cm: ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo = cm.activeNetworkInfo
            if (networkInfo == null) {
                value = false
                return
            }
            value = networkInfo.isConnected
        }
    }



    override fun onActive() {
        super.onActive()
        Log.e("life", "$tag      onActive")
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        Log.e("life","$tag      onInactive")

        context.unregisterReceiver(broadcastReceiver)
    }
}
