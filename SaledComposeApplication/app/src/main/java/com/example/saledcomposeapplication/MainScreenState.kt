package com.example.saledcomposeapplication

data class MainScreenState(
    var isCountButtonVisible : Boolean = false,
    var displayingResult: String = "",
    var inputValue : String = ""
)
