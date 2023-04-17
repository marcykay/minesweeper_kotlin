package minesweeper.userInterface

import minesweeper.conf.MAX_BOARD_SIZE
import minesweeper.conf.MIN_BOARD_SIZE
import minesweeper.conf.MIN_NUMBER_OF_MINES
import minesweeper.model.Coordinates
import minesweeper.util.convertAlphaCharToInt
import minesweeper.util.intToAlphaChar
import java.lang.IllegalArgumentException

class GameInputHandler {

    fun askForGameBoardSize(): Int {
        while (true) {
            print("Enter the size of the game board ($MIN_BOARD_SIZE..$MAX_BOARD_SIZE): ")
            val inputValue = readln().toIntOrNull()

            try {
                require(inputValue isNotNullAndIsRange MIN_BOARD_SIZE..MAX_BOARD_SIZE) {
                    "Input value must be between $MIN_BOARD_SIZE & $MAX_BOARD_SIZE"
                }
                return inputValue!!
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }
        }
    }

    fun askForNumberOfMines(maxNumberOfMines: Int): Int {
        while (true) {
            print("Enter the number bombs of the game board (1..$maxNumberOfMines): ")
            val inputValue = readln().toIntOrNull()

            try {
                require(inputValue isNotNullAndIsRange MIN_NUMBER_OF_MINES..maxNumberOfMines) {
                    "Input value must be between $MIN_NUMBER_OF_MINES & $maxNumberOfMines"
                }
                return inputValue!!
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }
        }
    }

    fun askForGameBoardCoordinates(boardSize: Int): Coordinates {
        val maxXCoordinateInAlphaChar = intToAlphaChar(boardSize)
        while (true) {
            print("Enter the coordinates (A1..$maxXCoordinateInAlphaChar$boardSize): ")
            val inputValue = readln().uppercase()

            val validatedCoordinate = convertStringToCoordinate(inputValue, boardSize)

            if (validatedCoordinate != null) return validatedCoordinate

            println("Incorrect coordinates, try again")
        }
    }

    fun askToContinuePlay(): Boolean {
        while (true) {
            print("Do you wish to start a new game? (Y / N): ")
            val inputValue = readln().uppercase()

            try {
                require(inputValue == "Y" || inputValue == "N") {
                    "Input value must be Y or N"
                }
                return inputValue == "Y"
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }
        }
    }

    private fun convertStringToCoordinate(inputValue: String, boardSize: Int): Coordinates? {
        val xCoordinate: Int?
        val yCoordinate: Int?

        if (inputValue.isNotBlank()) {
            xCoordinate = convertAlphaCharToInt(inputValue.first())
            yCoordinate = inputValue.substring(1).toIntOrNull()
        } else {
            return null
        }

        if (xCoordinate == null || xCoordinate > boardSize ||
            yCoordinate == null || yCoordinate > boardSize ||
            xCoordinate < 0 || yCoordinate - 1 < 0) {
            return null
        } else {
            return Coordinates(xCoordinate, yCoordinate - 1)
        }
    }

    private infix fun Int?.isNotNullAndIsRange(range: IntRange): Boolean = this != null && this in range
}

