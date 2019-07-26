package com.example.study.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.util.TextSpannableStringUtile
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
        color.text = TextSpannableStringUtile.changeAllTextColor(currentText, "#008577")
        color1.text = TextSpannableStringUtile.changeFirstTagColor(currentText, "22", "#008577")
        color2.text = TextSpannableStringUtile.changeAllTagColor(currentText, "22", "#008577")
    }
}
