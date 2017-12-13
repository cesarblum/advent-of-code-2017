package org.cesarbs.aoc2017.day07

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class Day07Test {
    val testInput = """pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)"""

    @Test fun testFindBaseProgram() {
        assertEquals("tknk", Day07.findBaseProgram(testInput).name)
    }

    @Test fun testFindUnbalancedProgram() {
        val unbalancedProgram = Day07.findUnbalancedProgram(testInput)
        assertEquals("ugml", unbalancedProgram.program.name)
        assertEquals(60, unbalancedProgram.expectedWeight)
    }

    @Test fun testParseProgramWithProgramsAbove() {
        val (name, weight, programsAbove) = Day07.parseProgram("tknk (41) -> ugml, padx, fwft")

        assertEquals("tknk", name)
        assertEquals(41, weight)
        assertEquals(listOf("ugml", "padx", "fwft"), programsAbove)
    }

    @Test fun testParseProgramWithNoProgramsAbove() {
        val (name, weight, programsAbove) = Day07.parseProgram("pbga (66)")

        assertEquals("pbga", name)
        assertEquals(66, weight)
        assertNotNull(programsAbove)
        assertTrue(programsAbove.isEmpty())
    }
}