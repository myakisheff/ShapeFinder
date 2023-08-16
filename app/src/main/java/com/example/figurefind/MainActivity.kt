 package com.example.figurefind


import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach


 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //TODO: creates a drawable file and show it in ImageView with id shapeToFind

        val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)
        shapeToFind.setImageResource(R.drawable.ic_shape_default)

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