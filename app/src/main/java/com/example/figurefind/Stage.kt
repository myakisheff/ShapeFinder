package com.example.figurefind

class Stage {
    private var stage: Int = 1

    fun getStage() = "Stage $stage"

    fun nextStage(){
        stage++
    }
}