package org.cesarbs.aoc2017.day10

import org.junit.Assert.assertEquals
import org.junit.Test

class Day10Test {
    @Test fun testHash1() {
        assertEquals(listOf(3, 4, 2, 1, 0), Day10.hash1(5, "3,4,1,5"))
    }

    @Test fun testHash2() {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.hash2(256, ""))
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.hash2(256, "AoC 2017"))
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.hash2(256, "1,2,3"))
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.hash2(256, "1,2,4"))
    }
}