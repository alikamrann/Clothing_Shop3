package com.example.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val serviceIntent = Intent(this,MyBackgroundService::class.java)
        serviceIntent.putExtra("NAME","ALI")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            Log.i("MYTAG","Start service")
            startService(serviceIntent)
        }
        binding.stopButton.setOnClickListener {
            Log.i("MYTAG","stop  service")
            stopService(serviceIntent)
        }
    }
}