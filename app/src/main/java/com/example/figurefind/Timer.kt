package com.example.figurefind

import android.os.CountDownTimer
import android.widget.TextView

class Timer(
    textView: TextView,
) {
    val txt = textView
    private var timer: CountDownTimer? = null
    private var remainingTime: Long = 0

    fun addTime(addedTimeInMills: Long){
        createNewTimer(remainingTime + addedTimeInMills)
    }

    fun removeTime(removedTimeInMills: Long){
        createNewTimer(remainingTime - removedTimeInMills)
    }

    fun createNewTimer(timeInMillis: Long) {
        timer?.cancel()

        timer = object: CountDownTimer(timeInMillis, 1000){
            override fun onTick(time: Long) {
                remainingTime = time

                val timeConvertedToSeconds = remainingTime / 1000

                if(timeConvertedToSeconds % 60 < 10)
                    txt.text =  "${timeConvertedToSeconds / 60}:0${timeConvertedToSeconds % 60}"
                else txt.text =  "${timeConvertedToSeconds / 60}:${timeConvertedToSeconds % 60}"

            }

            override fun onFinish() {
                txt.text = "Lose"
            }
        }.start()

    }
}