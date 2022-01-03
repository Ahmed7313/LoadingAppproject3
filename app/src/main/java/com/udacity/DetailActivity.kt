package com.udacity

import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var status: String
    private lateinit var fileName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        cancelNotification()

        intent.extras?.let {
            status = it.getString("status").toString()
            fileName = it.getString("filename").toString()
        }

        setTheState()

        back_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTheState() {
        filename_text.text = fileName
        status_text.text = status
        if (status.equals("success", true)) {
            status_text.setTextColor(Color.GREEN)
            animationDetails.setAnimation(R.raw.success)

        } else {
            status_text.setTextColor(Color.RED)
            animationDetails.setAnimation(R.raw.failed)
        }
    }

    private fun cancelNotification() {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelNotification()
    }
}
