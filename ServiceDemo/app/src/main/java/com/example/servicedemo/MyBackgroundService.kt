package com.example.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService: Service() {
    init {
        Log.i("MYTAG","Service has been created")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val name = intent?.getStringExtra("NAME")
        Log.i("MYTAG", "Service Started...$name")
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? =null

    override fun onDestroy() {
        Log.i("MYTAG","Service destroyed...")
        super.onDestroy()
    }
}