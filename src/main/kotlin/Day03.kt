package org.cesarbs.aoc2017.day03

import kotlin.coroutines.experimental.buildSequence
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

object Day03 {
    fun solvePart1(): Int = manhattanDistanceToSpiralCenter(input())

    fun solvePart2(): Int = stressTest().first { it > input() }

    fun manhattanDistanceToSpiralCenter(input: Int): Int {
        if (input == 1) {
            return 0
        }

        // Find the length of the side of the square the input sits in
        val sideLength = nextOdd(ceil(sqrt(input.toFloat())).toInt())

        // Distance to the center is the straight line distance from the
        // side of the square to the center, which is side length divided by 2,
        // plus the distance from the input to the middle of the side it sits in
        return sideLength / 2 + abs((input - 1) % (sideLength - 1) - sideLength / 2)
    }

    fun stressTest() = buildSequence {
        val memory = HashMap<Point, Int>()
        memory[Point(0, 0)] = 1
        yield(1)

        var sideLength = 3

        while (true) {
            for (pos in Square(sideLength).positions()) {
                val value = pos.neighbors().fold(0, { sum, neighbor -> sum + (memory[neighbor] ?: 0) })
                memory[pos] = value
                yield(value)
            }

            sideLength += 2
        }
    }

    private fun nextOdd(x: Int): Int = x + (x + 1) % 2

    private fun input() = 368078

    data class Point(val x: Int, val y: Int) {
        fun neighbors(): List<Point> = listOf(
            Point(x, y - 1),
            Point(x + 1, y - 1),
            Point(x + 1, y),
            Point(x + 1, y + 1),
            Point(x, y + 1),
            Point(x - 1, y + 1),
            Point(x - 1, y),
            Point(x - 1, y - 1))
    }

    class Square(length: Int) {
        private val startY = length / 2 - 1
        private val top = -length / 2
        private val bottom = length / 2
        private val left = -length / 2
        private val right = length / 2

        fun positions() = buildSequence {
            for (y in startY downTo top) {
                yield(Point(right, y))
            }

            for (x in (right - 1) downTo left) {
                yield(Point(x, top))
            }

            for (y in (top + 1)..bottom) {
                yield(Point(left, y))
            }

            for (x in (left + 1)..right) {
                yield(Point(x, bottom))
            }
        }
    }
}