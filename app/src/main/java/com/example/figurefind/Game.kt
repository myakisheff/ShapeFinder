package com.example.figurefind

class Game {
    private var timer: Int = 20
    private var stage: Int = 1

    fun getTimer() = "${timer / 60}:${timer % 60}"
    fun getStage() = "Stage $stage"

    fun addTime(seconds: Int){
        timer += seconds
    }

    fun nextStage(){
        stage++
    }

    private fun gameEnd(){

    }

}