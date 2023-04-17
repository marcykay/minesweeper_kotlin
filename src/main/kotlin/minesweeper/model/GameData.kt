package minesweeper.model

import minesweeper.conf.DEBUG_MODE

data class GameData(val gameBoardSize: Int, val maxNumOfMines: Int) {
    lateinit var minefield: Array<Array<Cell?>>
}

data class Coordinates(val x: Int, val y: Int)

class Cell(
    val coordindates: Coordinates,
    var hasMine: Boolean = false,
    var isCovered: Boolean = true,
) {

    var numberOfMinesAroundCell: Int = 0

    override fun toString(): String {
        return when {
            isCovered && DEBUG_MODE && hasMine-> "*"
            isCovered -> "_"
            !isCovered && hasMine -> "*"
            !isCovered && !hasMine && numberOfMinesAroundCell > 0 -> numberOfMinesAroundCell.toString()
            else -> " "
        }
    }

    fun showAll(): String {
        return when {
            hasMine -> "*"
            numberOfMinesAroundCell > 0 -> numberOfMinesAroundCell.toString()
            else -> " "
        }
    }
}
