package org.cesarbs.aoc2017.day04

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Day04Test {
    @Test fun testHasUniqueWords() {
        assertTrue(Day04.hasUniqueWords("aa bb cc dd ee"))
        assertFalse(Day04.hasUniqueWords("aa bb cc dd aa"))
        assertTrue(Day04.hasUniqueWords("aa bb cc dd aaa"))
    }

    @Test fun testHasUniqueAnagrams() {
        assertTrue(Day04.hasUniqueAnagrams("abcde fghij"))
        assertFalse(Day04.hasUniqueAnagrams("abcde xyz ecdab"))
        assertTrue(Day04.hasUniqueAnagrams("a ab abc abd abf abj"))
        assertTrue(Day04.hasUniqueAnagrams("iiii oiii ooii oooi oooo"))
        assertFalse(Day04.hasUniqueAnagrams("oiii ioii iioi iiio"))
    }
}