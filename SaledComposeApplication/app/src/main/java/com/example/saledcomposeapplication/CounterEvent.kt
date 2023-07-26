package com.example.saledcomposeapplication

sealed class CounterEvent{
    data class ValueEntered(val value : String):CounterEvent()
    object CountButtonClicked:CounterEvent()
    object ResetButonClicked : CounterEvent()
}
