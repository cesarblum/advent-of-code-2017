package org.cesarbs.aoc2017.day18

import org.junit.Assert.assertEquals
import org.junit.Test

class Day18Test {
    @Test fun testFirstRecoveredFrequency() {
        val testProgram = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""

        assertEquals(4, Day18.firstRecoveredFrequency(testProgram))
    }

    @Test fun testCountSndCalls() {
        val testProgram = """snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d"""

        // Should terminate due to deadlock
        assertEquals(3, Day18.countSndCalls(testProgram, 1))
    }
}