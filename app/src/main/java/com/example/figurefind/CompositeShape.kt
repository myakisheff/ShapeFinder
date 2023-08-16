package com.example.figurefind

import androidx.core.util.toRange

class CompositeShape(
    val countShapes: Int,
) {
    fun chooseShape(): String{
        return when(countShapes){
            2 -> {
                val range = 1..2
                if(range.random() == 1) "ic_first_composite_shape"
                else "ic_second_composite_shape"
            }
            else -> "ic_shape_square"
        }
    }
}