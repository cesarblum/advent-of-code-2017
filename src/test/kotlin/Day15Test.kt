package org.cesarbs.aoc2017.day15

import org.junit.Assert.assertEquals
import org.junit.Test

class Day15Test {
    @Test fun testCountMatches() {
        assertEquals(588, Day15.countMatches(65, 8921))
    }

    @Test fun testCountMatchesWithCriteria() {
        assertEquals(309, Day15.countMatchesWithCriteria(65, 8921))
    }
}