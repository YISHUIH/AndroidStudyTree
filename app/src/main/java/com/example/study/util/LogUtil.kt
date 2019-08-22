package com.example.study.util

import android.util.Log
import com.example.study.BuildConfig

/**
 * Copyright , 2015-2019 <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/22 16:52    <br>
 * Description: LogUtil   <br>
 */
class LogUtil {
    fun i(tag:String,content:String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag,content)
        }
    }

    fun w(tag:String,content:String) {
        if (BuildConfig.DEBUG) {
            Log.w(tag,content)
        }
    }

    fun d(tag:String,content:String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag,content)
        }
    }

    fun e(tag:String,content:String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag,content)
        }
    }

    fun iAlways(tag:String,content:String) {
        Log.i(tag,content)
    }

    fun wAlways(tag:String,content:String) {
        Log.w(tag,content)
    }

    fun dAlways(tag:String,content:String) {
        Log.d(tag,content)
    }

    fun eAlways(tag:String,content:String) {
        Log.e(tag,content)
    }
}