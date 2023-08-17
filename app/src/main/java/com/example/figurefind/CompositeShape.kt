package com.example.figurefind

class CompositeShape(
    private val countShapes: Int,
) {
    fun chooseShape(): Pair<String, List<String>> {
        //TODO: do a normal method... not this...
        // need to choose a shape to solve
        return when(countShapes){
            2 -> {
                val range = 1..2
                if(range.random() == 1) Pair("ic_first_composite_shape", listOf("circle", "square"))
                else Pair("ic_second_composite_shape", listOf("square", "rectangular"))
            }
            else -> Pair("ERROR", listOf())
        }
    }
}