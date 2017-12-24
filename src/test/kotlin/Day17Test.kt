package org.cesarbs.aoc2017.day17

import org.junit.Assert.assertEquals
import org.junit.Test

class Day17Test {
    @Test fun testFindValueNextToLastWrittenR() {
        assertEquals(638, Day17.findValueNextToLastWritten(2017, 3))
    }
}