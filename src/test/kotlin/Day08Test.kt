package org.cesarbs.aoc2017.day08

import org.junit.Assert.assertEquals
import org.junit.Test

class Day08Test {
    val testInput = """
b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10
"""

    @Test fun testFindLargestRegisterValue() {
        assertEquals(1, Day08.findLargestRegisterValue(testInput))
    }

    @Test fun testFindHighestSetValue() {
        assertEquals(10, Day08.findHighestSetValue(testInput))
    }
}