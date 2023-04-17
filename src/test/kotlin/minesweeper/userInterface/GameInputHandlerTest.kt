package minesweeper.userInterface

import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import io.mockk.mockkStatic
import minesweeper.model.Coordinates
import org.junit.jupiter.api.BeforeAll

class GameInputHandlerTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun init(): Unit {
            mockkStatic("kotlin.io.ConsoleKt")
        }
    }

    @Test
    fun `Check askForBoardSize gives valid value`() {

        val userInputHandler = GameInputHandler()
        every { readLine() } returnsMany listOf("3", "11","four", "", "5")

        assertEquals(userInputHandler.askForGameBoardSize(), 5)
    }

    @Test
    fun `Check askForNumberOfMines gives valid value`() {

        val userInputHandler = GameInputHandler()
        every { readLine() } returnsMany listOf("10", "two_bombs", "", "4")

        assertEquals(userInputHandler.askForNumberOfMines(5), 4)
    }

    @Test
    fun `Check askForGameBoardCoordinates gives valid value`() {
        val userInputHandler = GameInputHandler()
        every { readLine() } returnsMany listOf("A5", "G1", "AAas", "41", "A0", "D5", "D4")

        assertEquals(userInputHandler.askForGameBoardCoordinates(4), Coordinates(3,3))
    }

}