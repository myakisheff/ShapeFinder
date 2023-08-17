 package com.example.figurefind


import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach


 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //set timer
        val timer = Timer()
        val timerView = findViewById<TextView>(R.id.timer)
        timerView.text = timer.getTimer()

        //set stage
        val stage = Stage()
        val stageView = findViewById<TextView>(R.id.stage)
        stageView.text = stage.getStage()

        val solvingShape: String = CompositeShape(2).chooseShape()
        val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)

        //selecting a shape based on randomly chose shape
        if(solvingShape == "ic_first_composite_shape")
            shapeToFind.setImageResource(R.drawable.ic_first_composite_shape)
        else shapeToFind.setImageResource(R.drawable.ic_second_composite_shape)

        val fillColor = Color.rgb(160, 160, 160)
        shapeToFind.setColorFilter(fillColor)

        val viewGroup = findViewById<GridLayout>(R.id.gridLayout)
        viewGroup.forEach {
            if(it is ImageButton){
                it.setColorFilter(setRandomColor())
                it.setOnClickListener {
                    // TODO: check for a right shape or not and give some feedback
                }
            }
        }
    }

     private fun setRandomColor(): Int{
         val range = 0..255
         return Color.rgb(range.random(), range.random(), range.random())
     }

}