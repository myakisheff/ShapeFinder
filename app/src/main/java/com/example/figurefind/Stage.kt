package com.example.figurefind

class Stage {
    private var stage: Int = 1

    fun getStageString() = "Stage $stage"
    fun getStage() = stage

    fun nextStage(){
        stage++
    }
}