package com.example.figurefind

import kotlin.random.Random
import kotlin.random.nextInt

const val MAX_COUNT_OF_FIELDS = 9
class PlayerField {

    //return sorted set of numbers of fields which must be selected by player to win
    fun chooseRightFields(stages: Int): Set<Int> {

        val countOfFields = getNumberOfShapes(stages) //count of fields based on count of shapes

        val rightsSet = generateSequence {
            Random.nextInt(1..MAX_COUNT_OF_FIELDS)
        }
            .distinct()
            .take(countOfFields)
            .sorted()
            .toSet()

        return rightsSet
    }

    fun getNumberOfShapes(stages : Int): Int{
        return (stages - 1)/2 + 2
    }

}