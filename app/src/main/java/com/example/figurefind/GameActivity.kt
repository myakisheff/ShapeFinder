 package com.example.figurefind


import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach

 var rightShape = 0

 class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val arguments = intent.extras
        val stageNumber = arguments!!["stage"].toString()

        val timerView = findViewById<TextView>(R.id.timer)
        val stageView = findViewById<TextView>(R.id.stage)

        val timer = Timer(timerView)
        val stage = Stage(stageNumber.toInt())

        stageView.text = stage.getStageString()

        timer.createNewTimer(20000)

        val solvingShape: Pair<String, List<String>> = CompositeShape(2).chooseShape()
        val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)

        //selecting a shape based on randomly chose shape
        if(solvingShape.first == "ic_first_composite_shape")
            shapeToFind.setImageResource(R.drawable.ic_first_composite_shape)
        else shapeToFind.setImageResource(R.drawable.ic_second_composite_shape)

        val fillColor = Color.rgb(160, 160, 160)
        shapeToFind.setColorFilter(fillColor)

        val rightFields = PlayerField().chooseRightFields(stage.stage)

        val buttons = mutableMapOf<ImageButton, Int>()
        var field = 1
        val allShapes = Shapes()

        //player actions
        val viewGroup = findViewById<GridLayout>(R.id.gridLayout)
        viewGroup.forEach { button ->
            if(button is ImageButton){
                fillField(field, button, rightFields, solvingShape.second, allShapes)

                buttons[button] = field++

                button.setColorFilter(setRandomColor())
                button.setOnClickListener {
                    if(buttons[button] in rightFields)
                    {
                        button.setImageResource(R.drawable.ic_right_field)
                        button.setColorFilter(Color.GREEN)
                        timer.addTime(10000)
                    }
                    else {
                        button.setImageResource(R.drawable.ic_false_field)
                        button.setColorFilter(Color.RED)
                        timer.removeTime(5000)
                    }
                    button.setOnClickListener(null)
                }
            }
        }
    }

     private fun setRandomColor(): Int{
         val range = 0..255
         return Color.rgb(range.random(), range.random(), range.random())
     }

     private fun fillField(
         numberOfField: Int,
         buttonView: ImageButton,
         rightFields: Set<Int>,
         shapes: List<String>,
         allShapes: Shapes,
     ){

         val id: Int

         val stackShapes = allShapes.getShapes()
         val shapeNumber = (stackShapes.indices).random()

         if(numberOfField in rightFields){
             id  = resources.getIdentifier("ic_shape_${shapes[rightShape++]}", "drawable", packageName)
         }
         else{
             if(stackShapes[shapeNumber] !in shapes){
                 id = resources.getIdentifier("ic_shape_${stackShapes[shapeNumber]}", "drawable", packageName)
                 allShapes.removeShape(stackShapes[shapeNumber])
             }
             else{
                 fillField(numberOfField, buttonView, rightFields, shapes, allShapes)
                 return
             }

         }
         buttonView.setImageResource(id)
     }

}