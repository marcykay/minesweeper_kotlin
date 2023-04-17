package minesweeper.service

import minesweeper.conf.MAX_MINES_RATIO
import minesweeper.model.Cell
import minesweeper.model.Coordinates
import minesweeper.model.GameData
import kotlin.random.Random
import minesweeper.util.get

class GameBoard(private val gameData: GameData) {

    companion object {
        fun calculateMaximumNumberOfMines(gameBoardSize: Int): Int {
            return (gameBoardSize * gameBoardSize * MAX_MINES_RATIO).toInt()
        }
    }

    fun populateMineField() {
        println("Initialising Mine Field")
        initMineField()
        layingMinesRandomly()
        calculateHints()
    }

    fun openCell(coordinates: Coordinates) {
        gameData.minefield.get(coordinates)?.isCovered = false
        if (gameData.minefield.get(coordinates)?.numberOfMinesAroundCell == 0) {
            openConnectedEmptyCells(getNeighbours(coordinates))
        }
    }

    private fun openConnectedEmptyCells(neighbours: MutableList<Coordinates>) {
        neighbours.forEach {
            val thisCell = gameData.minefield.get(it)!!
            if (thisCell.isCovered && thisCell.numberOfMinesAroundCell == 0) {
                thisCell.isCovered = false
                openCell(it)
            }
        }
    }

    private fun calculateHints() {
        for (x in gameData.minefield.indices) {
            for (y in gameData.minefield[x].indices) {
                gameData.minefield[x][y]?.numberOfMinesAroundCell = countMinesAroundCell(
                    getNeighbours(gameData.minefield[x][y]!!.coordindates)
                )
            }
        }
    }


    private fun initMineField() {
        gameData.minefield = Array(gameData.gameBoardSize) { arrayOfNulls<Cell>(gameData.gameBoardSize) }
        for (x in gameData.minefield.indices) {
            for (y in gameData.minefield[x].indices) {
                gameData.minefield[x][y] = Cell(Coordinates(x, y))
            }
        }
    }

    private fun layingMinesRandomly() {
        var placedMines = 0
        while (placedMines < gameData.maxNumOfMines) {
            val x = randomInt()
            val y = randomInt()
            gameData.minefield[x][y]?.let {
                if (!it.hasMine) {
                    it.hasMine = true
                    placedMines++
                }
            }
        }
        println("Mines laid")
    }

    private fun randomInt() = Random.nextInt(0, gameData.gameBoardSize - 1)

    private fun getNeighbours(thisCell: Coordinates): MutableList<Coordinates> {
        val neighbourList = mutableListOf<Coordinates>()
        // Top Three
        if (thisCell.y - 1 >= 0) {
            if (thisCell.x - 1 >= 0) {
                neighbourList.add(Coordinates(thisCell.x - 1, thisCell.y - 1))
            }
            if (thisCell.x >= 0) {
                neighbourList.add(Coordinates(thisCell.x, thisCell.y - 1))
            }
            if (thisCell.x + 1 < gameData.gameBoardSize) {
                neighbourList.add(Coordinates(thisCell.x + 1, thisCell.y - 1))
            }
        }
        // Same Level
        if (thisCell.x - 1 >= 0) {
            neighbourList.add(Coordinates(thisCell.x - 1, thisCell.y))
        }
        if (thisCell.x + 1 < gameData.gameBoardSize) {
            neighbourList.add(Coordinates(thisCell.x + 1, thisCell.y))
        }
        // Lower Three
        if (thisCell.y + 1 < gameData.gameBoardSize) {
            if (thisCell.x - 1 >= 0) {
                neighbourList.add(Coordinates(thisCell.x - 1, thisCell.y + 1))
            }
            if (thisCell.x >= 0) {
                neighbourList.add(Coordinates(thisCell.x, thisCell.y + 1))
            }
            if (thisCell.x + 1 < gameData.gameBoardSize) {
                neighbourList.add(Coordinates(thisCell.x + 1, thisCell.y + 1))
            }
        }
        return neighbourList
    }

    private fun countMinesAroundCell(list: MutableList<Coordinates>): Int {
        var sumOfMinesAroundCell = 0
        list.forEach {
            if (gameData.minefield.get(it)?.hasMine == true) sumOfMinesAroundCell++
        }
        return sumOfMinesAroundCell
    }

}
