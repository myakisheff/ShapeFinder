package com.example.figurefind

class Stage (
    var stage: Int
){

    fun getStageString() = "Stage $stage"

    fun nextStage(){
        stage++
    }
}