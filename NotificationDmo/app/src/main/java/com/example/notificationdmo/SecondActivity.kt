package com.example.notificationdmo

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class  SecondActivity : AppCompatActivity() {
    private val KEY_REPLY = "key reply"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }
    private fun receiveInput(){
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput!=null){

            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            val  textView = findViewById<TextView>(R.id.textView3)
            textView.text = inputString

            val CHANNEL_ID = "com.example.notificationdmo"
            val notification_id = 100
            val replyNotification = NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_def_app_icon).setContentText("recived").build()

            val notificationManger : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManger.notify(notification_id,replyNotification )
        }
    }

}