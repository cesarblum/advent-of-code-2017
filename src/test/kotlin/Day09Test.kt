package org.cesarbs.aoc2017.day09

import org.junit.Assert.assertEquals
import org.junit.Test

class Day09Test {
    @Test fun testTotalScore() {
        assertEquals(1, Day09.totalScore("{}"))
        assertEquals(6, Day09.totalScore("{{{}}}"))
        assertEquals(5, Day09.totalScore("{{},{}}"))
        assertEquals(16, Day09.totalScore("{{{},{},{{}}}}"))
        assertEquals(1, Day09.totalScore("{<a>,<a>,<a>,<a>}"))
        assertEquals(9, Day09.totalScore("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        assertEquals(9, Day09.totalScore("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        assertEquals(3, Day09.totalScore("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
    }

    @Test fun testTotalGarbageLength() {
        assertEquals(0, Day09.totalGarbageLength("{}"))
        assertEquals(0, Day09.totalGarbageLength("{{{}}}"))
        assertEquals(0, Day09.totalGarbageLength("{{},{}}"))
        assertEquals(0, Day09.totalGarbageLength("{{{},{},{{}}}}"))
        assertEquals(4, Day09.totalGarbageLength("{<a>,<a>,<a>,<a>}"))
        assertEquals(8, Day09.totalGarbageLength("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        assertEquals(0, Day09.totalGarbageLength("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        assertEquals(17, Day09.totalGarbageLength("{{<a!>},{<a!>},{<a!>},{<ab>}}"))
    }

    @Test fun testParseGarbage() {
        assertEquals(0, Day09.parseGarbage(">".iterator()).length)
        assertEquals(17, Day09.parseGarbage("random characters>".iterator()).length)
        assertEquals(3, Day09.parseGarbage("<<<>".iterator()).length)
        assertEquals(2, Day09.parseGarbage("{!>}>".iterator()).length)
        assertEquals(0, Day09.parseGarbage("!!>".iterator()).length)
        assertEquals(0, Day09.parseGarbage("!!!>>".iterator()).length)
        assertEquals(10, Day09.parseGarbage("{o\"i!a,<{i<a>".iterator()).length)
    }
}