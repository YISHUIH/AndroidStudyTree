package com.example.study.activity.android

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.os.Process
import android.util.Log
import com.example.study.BaseActivity
import com.example.study.R
import kotlinx.android.synthetic.main.activity_caught_exception.*
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

class CaughtExceptionActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CaughtExceptionActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caught_exception)
        onRequestPermissions(arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE))

        bt.setOnClickListener {
            var s: String? = null
            s!!.get(0)
        }
    }

    override fun onPermissionDeny(permission: String) {

    }

    override fun onPermissionAllow(permission: String) {
        Thread.setDefaultUncaughtExceptionHandler(MyCaughtException())
    }


    /**
     * 自定义的异常管理器
     */
    class MyCaughtException : Thread.UncaughtExceptionHandler {
        var mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()

        init {
            Thread.setDefaultUncaughtExceptionHandler(this)
        }

        override fun uncaughtException(t: Thread?, e: Throwable?) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                val logDirs = File(Environment.getExternalStorageDirectory(), "/log")
                if (!logDirs.exists()) {
                    logDirs.mkdirs()
                }
                val file = File(logDirs, "CaughtError.txt")
                if (!file.exists()) {
                    file.createNewFile()
                }
                val pw = PrintWriter(FileOutputStream(file, true))
                val sp=SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

                pw.println("----- 开始: $sp -----")
                e?.printStackTrace(pw)
                pw.println("----- 结束: $sp -----")
                pw.close()
            }
            e?.printStackTrace()
            System.err.println("ssssssssssssssssssssssssssssssssss")
            Log.e("CaughtException", "threadName:    ${t?.name}    msg:   ${e?.message}")
            if (mDefaultCrashHandler != null) {
                mDefaultCrashHandler.uncaughtException(t, e)
            } else {
                Process.killProcess(Process.myPid())
            }


        }
    }
}
