package org.cesarbs.aoc2017.day13

import org.junit.Assert.assertEquals
import org.junit.Test

class Day13Test {
    private val testFirewall = Day13.Firewall(listOf(
        Day13.FirewallLayer(0, 3),
        Day13.FirewallLayer(1, 2),
        Day13.FirewallLayer(4, 4),
        Day13.FirewallLayer(6, 4)
    ))

    @Test fun testTripSeverity() {
        assertEquals(24, Day13.tripSeverity(testFirewall))
    }

    @Test fun testSafeTripStartTime() {
        assertEquals(10, Day13.safeTripStartTime(testFirewall))
    }
}