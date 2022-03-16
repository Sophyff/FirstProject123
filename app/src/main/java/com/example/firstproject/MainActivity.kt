package com.example.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.firstproject.activities.DrawNavActivity
import com.example.firstproject.activities.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("appsettings", MODE_PRIVATE)

        if(pref.contains("user")) {
            handler.sendEmptyMessageDelayed(MESSAGE_GOTO_DASHBOARD, 3000)
        } else {
            handler.sendEmptyMessageDelayed(MESSAGE_GOTO_LOGIN, 3000)
        }
    }

    val handler = object: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what == MESSAGE_GOTO_DASHBOARD) {
               startActivity(Intent(baseContext, DrawNavActivity::class.java))
                finish()
            } else if(msg.what == MESSAGE_GOTO_LOGIN) {
               startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val MESSAGE_GOTO_LOGIN = 1
        const val MESSAGE_GOTO_DASHBOARD = 2
    }
}