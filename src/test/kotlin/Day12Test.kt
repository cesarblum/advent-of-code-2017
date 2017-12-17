package org.cesarbs.aoc2017.day12

import org.junit.Assert.assertEquals
import org.junit.Test

class Day12Test {
    private val testInput = listOf(
        Day12.Program(0, listOf(2)),
        Day12.Program(1, listOf(1)),
        Day12.Program(2, listOf(0, 3, 4)),
        Day12.Program(3, listOf(2, 4)),
        Day12.Program(4, listOf(2, 3, 6)),
        Day12.Program(5, listOf(6)),
        Day12.Program(6, listOf(4, 5))
    )

    @Test fun testProgramsInGroup() {
        assertEquals(setOf(0, 2, 3, 4, 5, 6), Day12.programsInGroup(0, testInput))
    }

    @Test fun testCountGroups() {
        assertEquals(2, Day12.countGroups(testInput))
    }

    @Test fun testParseInputLine() {
        assertEquals(Day12.Program(0, listOf(2)), Day12.parseInputLine("0 <-> 2"))
    }
}