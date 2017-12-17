package org.cesarbs.aoc2017.day11

import org.junit.Assert.assertEquals
import org.junit.Test

class Day11Test {
    @Test fun testHexGridDistance() {
        assertEquals(Day11.HexGridWalkStats(3, 3), Day11.hexGridWalkStats("ne,ne,ne"))
        assertEquals(Day11.HexGridWalkStats(0, 2), Day11.hexGridWalkStats("ne,ne,sw,sw"))
        assertEquals(Day11.HexGridWalkStats(2, 2), Day11.hexGridWalkStats("ne,ne,s,s"))
        assertEquals(Day11.HexGridWalkStats(3, 3), Day11.hexGridWalkStats("se,sw,se,sw,sw"))
    }
}