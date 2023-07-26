package com.example.saledcomposeapplication

import android.os.Message

sealed class UIEvent{
    data class ShowMessage(val message: String):UIEvent()
}
