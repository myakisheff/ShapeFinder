package com.example.figurefind

class CompositeShape(
    private val countShapes: Int,
) {
    fun chooseShape(): Pair<String, List<String>> {
        //TODO: do a normal method... not this...
        // need to choose a shape to solve

        //maybe not so bad...
        return when(countShapes){
            2 -> {
                val range = 1..2
                if(range.random() == 1) Pair("first", listOf("square", "rectangular"))
                else Pair("second", listOf("circle", "square"))
            }
            3 -> {
                val range = 1..2
                if(range.random() == 1) Pair("third", listOf("donut", "square", "triangle"))
                else Pair("fourth", listOf("triangle", "hexagon", "trapezoid"))
            }
            else -> Pair("ERROR", listOf())
        }
    }
}