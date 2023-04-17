package minesweeper.service

import minesweeper.model.Cell
import minesweeper.model.GameData
import minesweeper.userInterface.GameBoardRenderer
import minesweeper.userInterface.GameInputHandler
import minesweeper.util.get

class GameService {

    private val inputHandler = GameInputHandler()
    private val boardRenderer = GameBoardRenderer()
    private lateinit var gameBoard: GameBoard
    private lateinit var gameData: GameData

    fun play() {
        var newGame = true
        while (newGame) {
            prepareGame()
            startGame()
            if (!inputHandler.askToContinuePlay()) newGame = false
        }
    }

    private fun prepareGame() {
        val gameBoardSize = inputHandler.askForGameBoardSize()
        val numOfMines = inputHandler.askForNumberOfMines(GameBoard.calculateMaximumNumberOfMines(gameBoardSize))

        gameData = GameData(gameBoardSize, numOfMines)
        gameBoard = GameBoard(gameData)
        gameBoard.populateMineField()

//        println("--".repeat(10))
//        println("Hints")
//        println("--".repeat(10))
//        printMap(gameData.minefield)
//        println("--".repeat(10))
    }

    private fun startGame() {
        var inGame = true
        while (inGame) {
            boardRenderer.printGameBoard(gameData.minefield)
            val inputCoordinates = inputHandler.askForGameBoardCoordinates(gameData.gameBoardSize)
            gameBoard.openCell(inputCoordinates)

            if (gameData.minefield.get(inputCoordinates)?.hasMine == true) {
                println("MINE EXPLODED! GAME OVER!")
                inGame = false
            }
        }
    }

    private fun printMap(boardArray: Array<Array<Cell?>>) {
        for (x in boardArray.indices) {
            for (y in boardArray[x].indices) {
                val num = "${boardArray[x][y]?.showAll()}"
                print("$num ")
            }
            println()
        }
    }
}


