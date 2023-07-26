package com.example.twowaydemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twowaydemo.databinding.ActivityMainBinding

class MainActivityViewModel : ViewModel() {

    val userName = MutableLiveData<String>()
    init {
        userName.value = "Frank"
    }
}