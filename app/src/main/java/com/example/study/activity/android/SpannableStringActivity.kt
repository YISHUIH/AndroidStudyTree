package com.example.study.activity.android

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.util.MyClickableSpan
import com.example.study.util.TextSpannableStringUtil
import kotlinx.android.synthetic.main.activity_spannable_string.*

class SpannableStringActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {

            val intent = Intent(context, SpannableStringActivity::class.java)
            context.startActivity(intent)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)
        val currentText = "22222121221121212212111212211121212121222222"

        current.text = currentText
        color.text = TextSpannableStringUtil.changeAllTextColor(currentText, ForegroundColorSpan(Color.GREEN))!!
        color1.text = TextSpannableStringUtil.changeFirstTagColor(currentText, "22", ForegroundColorSpan(Color.GREEN))
        color2.text = TextSpannableStringUtil.changeAllTagColor(currentText, "22", Color.BLUE)




        val text="几岁看到非就是来的《用户协议》《隐私协议》"
        val sq=text.indexOf("《用户协议》")
        val sss=text.subSequence(0,sq+6) as String
        val sss1=text.subSequence(sq+6,text.length) as String
        val s= TextSpannableStringUtil.changeFirstTagColor(sss,"《用户协议》",object : MyClickableSpan(Color.BLUE){
            override fun onClick(widget: View) {
                Toast.makeText(this@SpannableStringActivity,"《用户协议》",Toast.LENGTH_SHORT).show()
            }
        })
        val s2= TextSpannableStringUtil.changeFirstTagColor(sss1,"《隐私协议》",object :MyClickableSpan(Color.BLUE){
            override fun onClick(widget: View) {
                Toast.makeText(this@SpannableStringActivity,"《隐私协议》",Toast.LENGTH_SHORT).show()
            }
        })

        val sb=SpannableStringBuilder()
        sb.append(s).append(s2)
        color3.movementMethod = LinkMovementMethod()
        color3.text=sb
        color3.highlightColor=Color.TRANSPARENT
    }
}
