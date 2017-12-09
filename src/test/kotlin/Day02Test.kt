package org.cesarbs.aoc2017.day02

import org.junit.Assert.assertEquals
import org.junit.Test

class Day02Test {
    @Test fun testChecksumPart01() {
        val input = listOf(
            listOf(5, 1, 9, 5),
            listOf(7, 5, 3),
            listOf(2, 4, 6, 8))

        assertEquals(18, Day02.checksumPart01(input))
    }

    @Test fun testChecksumPart02() {
        val input = listOf(
            listOf(5, 9, 2, 8),
            listOf(9, 4, 8, 3),
            listOf(3, 8, 6, 5))

        assertEquals(9, Day02.checksumPart02(input))
    }
}