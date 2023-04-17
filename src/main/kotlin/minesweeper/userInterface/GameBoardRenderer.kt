package minesweeper.userInterface

import minesweeper.model.Cell
import minesweeper.util.intToAlphaChar

class GameBoardRenderer {

    fun printGameBoard(boardArray: Array<Array<Cell?>>) {
        printMap(addCoordinatesLabels(boardArray))
    }

    private fun addCoordinatesLabels(sourceArray: Array<Array<Cell?>>): Array<Array<String>> {
        val numRows = sourceArray.size
        val numCols = sourceArray[0].size

        // Create a new 2D array with dimensions (numRows + 1) x (numCols + 1)
        val gridArray = Array(numRows + 1) { Array(numCols + 1) { " " } }

        // Fill in the horizontal axis labels (numbers)
        for (j in 1..numCols) {
            gridArray[0][j] = j.toString()
        }

        // Fill in the vertical axis labels (letters)
        for (i in 1..numRows) {
            gridArray[i][0] = intToAlphaChar(i).toString()
        }

        // Fill in the data values from the original array
        for (i in 1..numRows) {
            for (j in 1..numCols) {
                gridArray[i][j] = sourceArray[i - 1][j - 1]?.toString() ?: "*"
            }
        }

        return gridArray
    }

    fun printMap(boardArray: Array<Array<String>>) {
        for (x in boardArray.indices) {
            for (y in boardArray[x].indices) {
                print("${boardArray[x][y]} ")
            }
            println()
        }
    }
}