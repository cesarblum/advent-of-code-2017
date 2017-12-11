package org.cesarbs.aoc2017.day06

import org.junit.Assert.assertEquals
import org.junit.Test

class Day06Test {
    @Test fun testReallocationsUntilCycle() {
        assertEquals(Pair(5, 1), Day06.reallocationsUntilCycle(listOf(0, 2, 7, 0), { loopIteration, stateIteration -> Pair(loopIteration, stateIteration)}))
    }

    @Test fun testReallocateMemory() {
        assertEquals(listOf(2, 4, 1, 2), Day06.reallocateMemory(listOf(0, 2, 7, 0)))
        assertEquals(listOf(3, 1, 2, 3), Day06.reallocateMemory(listOf(2, 4, 1, 2)))
        assertEquals(listOf(0, 2, 3, 4), Day06.reallocateMemory(listOf(3, 1, 2, 3)))
        assertEquals(listOf(1, 3, 4, 1), Day06.reallocateMemory(listOf(0, 2, 3, 4)))
        assertEquals(listOf(2, 4, 1, 2), Day06.reallocateMemory(listOf(1, 3, 4, 1)))
    }
}