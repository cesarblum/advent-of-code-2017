package org.cesarbs.aoc2017.day05

import org.junit.Assert.assertEquals
import org.junit.Test

class Day05Test {
    @Test fun testCountJumps() {
        assertEquals(5, Day05.countJumps(listOf(0, 3, 0, 1, -3), Day05::part1JumpUpdate))
        assertEquals(10, Day05.countJumps(listOf(0, 3, 0, 1, -3), Day05::part2JumpUpdate))
    }
}