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

        val timer = Timer()
        val stage = Stage()

        initInfoBar(timer, stage)

        val solvingShape: String = CompositeShape(2).chooseShape()
        val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)

        //selecting a shape based on randomly chose shape
        if(solvingShape == "ic_first_composite_shape")
            shapeToFind.setImageResource(R.drawable.ic_first_composite_shape)
        else shapeToFind.setImageResource(R.drawable.ic_second_composite_shape)

        val fillColor = Color.rgb(160, 160, 160)
        shapeToFind.setColorFilter(fillColor)

        val rightFields = PlayerField().chooseRightFields(stage.getStage())

        val buttons = mutableMapOf<ImageButton, Int>()
        var field = 1

        val viewGroup = findViewById<GridLayout>(R.id.gridLayout)
        viewGroup.forEach { button ->
            if(button is ImageButton){

                buttons[button] = field++

                button.setColorFilter(setRandomColor())
                button.setOnClickListener {
                    if(buttons[button] in rightFields)
                    {
                        button.setImageResource(R.drawable.ic_right_field)
                        button.setColorFilter(Color.GREEN)
                    }
                    else {
                        button.setImageResource(R.drawable.ic_false_field)
                        button.setColorFilter(Color.RED)
                    }
                    button.setOnClickListener(null)
                }
            }
        }
    }

     private fun initInfoBar(timer: Timer, stage: Stage){
         //set timer
         val timerView = findViewById<TextView>(R.id.timer)
         timerView.text = timer.getTimer()

         //set stage
         val stageView = findViewById<TextView>(R.id.stage)
         stageView.text = stage.getStageString()
     }

     private fun setRandomColor(): Int{
         val range = 0..255
         return Color.rgb(range.random(), range.random(), range.random())
     }

}