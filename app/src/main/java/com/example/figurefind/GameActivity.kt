 package com.example.figurefind


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach

 var rightShape = 0

 @Suppress("DEPRECATION")
 class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        rightShape = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val arguments = intent.extras
        val stageNumber = arguments!!["stage"].toString()

        val timerView = findViewById<TextView>(R.id.timer)
        val stageView = findViewById<TextView>(R.id.stage)

        val timer = Timer(timerView)
        val stage = Stage(stageNumber.toInt())

        timer.createNewTimer(20000)
        stageView.text = stage.getStageString()

        val playerField = PlayerField()
        val countShapes = playerField.getNumberOfShapes(stageNumber.toInt())

        val solvingShape: Pair<String, List<String>> = CompositeShape(countShapes).chooseShape()

        if(solvingShape.first == "ERROR")
        {
            val menu = Intent(this, MenuActivity::class.java)
            startActivity(menu)
            finish()
            Toast.makeText(this, "Game Completed", Toast.LENGTH_LONG).show()
        }

        drawCompositeShape(solvingShape.first)

        val rightFields = playerField.chooseRightFields(stage.stage)

        val buttons = mutableMapOf<ImageButton, Int>()
        var field = 1
        val allShapes = Shapes()

        var rightChooses = 0
        var falseChooses = 0

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

                        rightChooses++
                        if (rightChooses == countShapes){
                            val game = Intent(this, GameActivity::class.java)
                            stage.nextStage()
                            game.putExtra("stage", stage.stage)
                            startActivity(game)
                            finish()
                            overridePendingTransition(0, 1)
                        }
                    }
                    else {
                        button.setImageResource(R.drawable.ic_false_field)
                        button.setColorFilter(Color.RED)
                        timer.removeTime(5000)
                        falseChooses++

                        if (timerView.text == "Lose"){
                            val menu = Intent(this, MenuActivity::class.java)
                            startActivity(menu)
                            finish()

                            //TODO: Rewrite this ... to show activity with result
                        }
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

     @SuppressLint("DiscouragedApi")
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


     @SuppressLint("DiscouragedApi")
     private fun drawCompositeShape(compositeShape : String){
         val shapeToFind = findViewById<ImageView>(R.id.shapeToFind)

         val resID = resources.getIdentifier("ic_composite_shape_$compositeShape", "drawable", packageName)
         shapeToFind.setImageResource(resID)

         val fillColor = Color.rgb(160, 160, 160)
         shapeToFind.setColorFilter(fillColor)
     }
}