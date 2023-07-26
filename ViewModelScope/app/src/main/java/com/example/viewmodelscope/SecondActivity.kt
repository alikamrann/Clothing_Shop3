package com.example.viewmodelscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.example.viewmodelscope.ui.main.SecondFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        progressBar = findViewById(R.id.progressBar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance())
                .commitNow()
        }
        lifecycleScope.launch(Dispatchers.IO) {
            Log.i("MyTag","thread is : ${Thread.currentThread().name}")
//            delay(5000)
//            progressBar.visibility = View.VISIBLE
//            delay(10000)
//            progressBar.visibility = View.GONE
        }
    }
}