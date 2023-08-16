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

        val game = Game()

        //set timer
        val timer = findViewById<TextView>(R.id.timer)
        timer.text = game.getTimer()

        //set stage
        val stage = findViewById<TextView>(R.id.stage)
        stage.text = game.getStage()

        //TODO: creates a drawable file and show it in ImageView with id shapeToFind

        val solvingShape: String = CompositeShape(2).chooseShape()

        val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)

        if(solvingShape == "ic_first_composite_shape")
            shapeToFind.setImageResource(R.drawable.ic_first_composite_shape)
        else shapeToFind.setImageResource(R.drawable.ic_second_composite_shape)

        val darkColor = Color.rgb(160, 160, 160)
        shapeToFind.setColorFilter(darkColor)

        val viewGroup = findViewById<GridLayout>(R.id.gridLayout)
        viewGroup.forEach {
            if(it is ImageButton){
                it.setColorFilter(setRandomColor())
            }
        }
    }

     private fun setRandomColor(): Int{
         val range = 0..255
         return Color.rgb(range.random(), range.random(), range.random())
     }

}