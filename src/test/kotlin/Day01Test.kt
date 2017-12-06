package org.cesarbs.aoc2017.day01

import org.junit.Assert.assertEquals
import org.junit.Test

class Day01Test {
    @Test fun givenSequenceOfDigits_whenSolvePart1_thenSuccess() {
        assertEquals(3, Day01.solvePart1("1122"))
        assertEquals(4, Day01.solvePart1("1111"))
        assertEquals(0, Day01.solvePart1("1234"))
        assertEquals(9, Day01.solvePart1("91212129"))
    }

    @Test fun givenSequenceOfDigits_whenSolvePart2_thenSuccess() {
        assertEquals(6, Day01.solvePart2("1212"))
        assertEquals(0, Day01.solvePart2("1221"))
        assertEquals(4, Day01.solvePart2("123425"))
        assertEquals(12, Day01.solvePart2("123123"))
        assertEquals(4, Day01.solvePart2("12131415"))
    }
}