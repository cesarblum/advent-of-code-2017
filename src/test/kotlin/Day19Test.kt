package org.cesarbs.aoc2017.day19

import org.junit.Assert.assertEquals
import org.junit.Test

class Day19Test {
    @Test fun testFollowDiagram() {
        val testDiagram = listOf(
            "     |          ",
            "     |  +--+    ",
            "     A  |  C    ",
            " F---|----E|--+ ",
            "     |  |  |  D ",
            "     +B-+  +--+ ",
            "                ")

        assertEquals(
            Day19.DiagramStats(38, listOf('A', 'B', 'C', 'D', 'E', 'F')),
            Day19.followDiagram(testDiagram))
    }
}