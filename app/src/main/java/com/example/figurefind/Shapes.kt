package com.example.figurefind

class Shapes {

    private val listOfShapes = mutableListOf<String>()

    init {
        //perfect code...
        listOfShapes.add("circle")
        listOfShapes.add("donut")
        listOfShapes.add("heart")
        listOfShapes.add("hexagon")
        listOfShapes.add("parallelogram")
        listOfShapes.add("rectangular")
        listOfShapes.add("square")
        listOfShapes.add("star")
        listOfShapes.add("trapezoid")
        listOfShapes.add("triangle")
    }

    fun getShapes(): List<String> {
        return listOfShapes
    }

    fun removeShape(shape: String){
        listOfShapes.remove(shape)
    }
}