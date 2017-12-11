package org.cesarbs.aoc2017.day03

import org.junit.Assert.assertEquals
import org.junit.Test

class Day03Test {
    @Test fun testManhattanDistanceToSpiralCenter() {
        assertEquals(0, Day03.manhattanDistanceToSpiralCenter(1))
        assertEquals(3, Day03.manhattanDistanceToSpiralCenter(12))
        assertEquals(2, Day03.manhattanDistanceToSpiralCenter(23))
        assertEquals(31, Day03.manhattanDistanceToSpiralCenter(1024))
    }

    @Test fun testStressTest() {
        assertEquals(
            listOf(1, 1, 2, 4, 5, 10, 11, 23, 25, 26, 54, 57, 59, 122, 133, 142, 147, 304, 330, 351, 362, 747, 806),
            Day03.stressTest().take(23).toList())
    }
}