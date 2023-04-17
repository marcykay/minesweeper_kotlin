package minesweeper.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HelperTest {

    @Test
    fun convertCharToInt() {
        assertEquals(0, convertAlphaCharToInt('A'))
        assertEquals(9, convertAlphaCharToInt('J'))
        assertEquals(null, convertAlphaCharToInt('1'))
    }

    @Test
    fun `test intToChar`() {
        assertEquals('A', intToAlphaChar(1))
        assertEquals('J', intToAlphaChar(10))
    }
}