package org.cesarbs.aoc2017.day13

import java.util.*
import kotlin.coroutines.experimental.buildSequence

object Day13 {
    data class FirewallLayer(val depth: Int, val range: Int)

    enum class ScannerDirection {
        UP, DOWN
    }

    data class Scanner(val layer: FirewallLayer, val position: Int, val direction: ScannerDirection) {
        fun update(): Scanner =
            when (direction) {
                ScannerDirection.UP -> Scanner(layer,
                    position - 1,
                    if (position - 1 == 0) ScannerDirection.DOWN else ScannerDirection.UP)
                ScannerDirection.DOWN -> Scanner(layer,
                    position + 1,
                    if (position + 1 == layer.range - 1) ScannerDirection.UP else ScannerDirection.DOWN)
            }
    }

    data class Firewall private constructor(val scanners: Map<Int, Scanner>) {
        val depth = scanners.values.maxBy { it.layer.depth }!!.layer.depth

        constructor(layers: List<FirewallLayer>)
            : this(layers.associate { Pair(it.depth, Scanner(it, 0, ScannerDirection.DOWN)) })

        fun update(): Firewall = Firewall(scanners.mapValues { it.value.update() })
    }

    fun solvePart1(): Int = tripSeverity(Firewall(parseInput(input())))

    fun solvePart2(): Int = safeTripStartTime(Firewall(parseInput(input())))

    fun tripSeverity(firewall: Firewall): Int {
        var state = firewall
        var severity = 0

        for (layer in 0..state.depth) {
            val scanner = state.scanners[layer]

            if (scanner?.position == 0) {
                severity += scanner.layer.depth * scanner.layer.range
            }

            state = state.update()
        }

        return severity
    }

    fun safeTripStartTime(firewall: Firewall): Int {
        val stateIterator = firewallStates(firewall).iterator()
        val states = LinkedList((0..firewall.depth).map { stateIterator.next() })
        var time = 0

        loop@ while (true) {
            for ((layer, state) in states.withIndex()) {
                val scanner = state.scanners[layer]

                if (scanner?.position == 0) {
                    states.removeFirst()
                    states.add(stateIterator.next())
                    time++
                    continue@loop
                }
            }

            return time
        }
    }

    private fun firewallStates(firewall: Firewall) = buildSequence {
        var currentState = firewall

        while (true) {
            yield(currentState)
            currentState = currentState.update()
        }
    }

    private fun parseInput(input: String): List<FirewallLayer> = input.lines().map(::parseInputLine)

    private fun parseInputLine(line: String) = line
        .split(": ".toRegex())
        .map(String::toInt)
        .let { FirewallLayer(it[0], it[1]) }

    private fun input() = """0: 3
1: 2
2: 4
4: 6
6: 5
8: 8
10: 6
12: 4
14: 8
16: 6
18: 8
20: 8
22: 6
24: 8
26: 9
28: 12
30: 8
32: 14
34: 10
36: 12
38: 12
40: 10
42: 12
44: 12
46: 12
48: 12
50: 14
52: 12
54: 14
56: 12
60: 14
62: 12
64: 14
66: 14
68: 14
70: 14
72: 14
74: 14
78: 26
80: 18
82: 17
86: 18
88: 14
96: 18"""
}