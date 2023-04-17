package minesweeper.util

import minesweeper.model.Cell
import minesweeper.model.Coordinates

private val charList = ('A'..'Z').toList()

fun convertAlphaCharToInt(value: Char): Int? {
    val index = charList.indexOf(value)
    return if (index != -1) index else null
}

fun intToAlphaChar(value: Int): Char? {
   return try {
        charList[value - 1]
    } catch (e: IndexOutOfBoundsException) {
        null
    }
}

fun Array<Array<Cell?>>.get(coordinate: Coordinates): Cell? {
    return this.getOrNull(coordinate.x)?.getOrNull(coordinate.y)
}