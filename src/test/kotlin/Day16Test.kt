package org.cesarbs.aoc2017.day16

import org.junit.Assert.assertEquals
import org.junit.Test

class Day16Test {
    @Test fun testDance() {
        val testDancerCount = 5
        val testMoves = "s1,x3/4,pe/b"
        assertEquals("baedc", Day16.dance(testDancerCount, 1, testMoves))
        assertEquals("ceadb", Day16.dance(testDancerCount, 2, testMoves))
    }
}