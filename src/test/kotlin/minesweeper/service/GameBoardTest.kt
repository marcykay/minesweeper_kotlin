package minesweeper.service

import minesweeper.model.GameData
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class GameBoardTest {

    @Test
    fun calculateMaximumNumberOfMines() {
        val gameBoardSize = 10
        assertEquals(38, GameBoard.calculateMaximumNumberOfMines(gameBoardSize))
    }

    @Test
    fun `test populateMineField yields correct total number of mines`() {
        val gameData = GameData(4,2)
        val gameBoard = GameBoard(gameData)
        var numOfMines = 0
        gameBoard.populateMineField()

        for (x in gameData.minefield.indices) {
            for (y in gameData.minefield[x].indices) {
                if (gameData.minefield[x][y]?.hasMine == true) {
                    numOfMines++
                }
            }
        }
        assertEquals(numOfMines, gameData.maxNumOfMines)
    }
}