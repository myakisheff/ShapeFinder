package com.example.figurefind

import android.widget.TextView

class Timer {
    private var timer: Int = 20

    fun getTimer() = "${timer / 60}:${timer % 60}"

    fun addTime(seconds: Int){
        timer += seconds
    }
}