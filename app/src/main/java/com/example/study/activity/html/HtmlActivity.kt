package com.example.study.activity.html

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.example.study.BaseActivity
import com.example.study.R
import kotlinx.android.synthetic.main.activity_html.*

class HtmlActivity : BaseActivity() {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HtmlActivity::class.java)
            context.startActivity(intent)
        }
    }

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html)

        val ws = wv.settings
        ws.javaScriptEnabled = true

        wv.loadUrl("file:///android_asset/index.html")

        wv.addJavascriptInterface(JsInterface(), "android")

        bt.setOnClickListener {
            //有参无返回
            wv.loadUrl("javascript:alertMessage(\"你好\")")

        }
        bt1.setOnClickListener {

            //有返回值
            getReturnFromH5()
        }
        bt2.setOnClickListener {
            //无参无返回
            wv.loadUrl("javascript:show()")
        }
        wv.webChromeClient = WebChromeClient()
    }


    fun getReturnFromH5() {
        wv.evaluateJavascript("javascript:sum(1,2)"){
            bt1.text=it
        }
    }

    class JsInterface {
        @JavascriptInterface
        fun back(): String {

            return "Android  返回"
        }
    }
}
