package com.example.dependenyinjectiondemo

import android.util.Log
import javax.inject.Inject

class NickelCadmiumBattery @Inject constructor():Battery {
    override fun getPower() {
        Log.i("MYTAG","Get power from NickelCadmiumBattery")
    }
}