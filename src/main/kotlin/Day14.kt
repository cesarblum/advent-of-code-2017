package org.cesarbs.aoc2017.day14

import org.cesarbs.aoc2017.day10.Day10
import java.util.*

object Day14 {
    data class Coordinate(val x: Int, val y: Int) {
        fun adjacent(): List<Coordinate> = listOf(
            Coordinate(x, y - 1),
            Coordinate(x, y + 1),
            Coordinate(x + 1, y),
            Coordinate(x - 1, y))
    }

    class Grid(gridRows: List<List<Int>>) : Iterable<List<Int>> {
        private val grid = gridRows.map(Collection<Int>::toMutableList)

        val rows = gridRows.size

        val columns = gridRows[0].size

        operator fun get(at: Coordinate): Int =
            if (at.y in 0..(rows - 1) && at.x in 0..(columns - 1)) {
                grid[at.y][at.x]
            } else {
                0
            }

        operator fun set(at: Coordinate, value: Int) {
            grid[at.y][at.x] = value
        }

        override fun iterator(): Iterator<List<Int>> = grid.iterator()
    }

    private val bits = mapOf(
        '0' to listOf(0, 0, 0, 0),
        '1' to listOf(0, 0, 0, 1),
        '2' to listOf(0, 0, 1, 0),
        '3' to listOf(0, 0, 1, 1),
        '4' to listOf(0, 1, 0, 0),
        '5' to listOf(0, 1, 0, 1),
        '6' to listOf(0, 1, 1, 0),
        '7' to listOf(0, 1, 1, 1),
        '8' to listOf(1, 0, 0, 0),
        '9' to listOf(1, 0, 0, 1),
        'a' to listOf(1, 0, 1, 0),
        'b' to listOf(1, 0, 1, 1),
        'c' to listOf(1, 1, 0, 0),
        'd' to listOf(1, 1, 0, 1),
        'e' to listOf(1, 1, 1, 0),
        'f' to listOf(1, 1, 1, 1))

    fun solvePart1() = grid(input()).fold(0, { acc, row -> acc + row.sum() })

    fun solvePart2() = countRegions(grid(input()))

    private fun grid(input: String): Grid = Grid(
        (0..127)
            .map { row -> Day10.knotHash(256, "$input-$row") }
            .map { hash -> hash.flatMap { bits[it]!! } })

    private fun countRegions(grid: Grid): Int {
        var regions = 0

        for (y in 0..(grid.rows - 1)) {
            for (x in 0..(grid.columns - 1)) {
                val at = Coordinate(x, y)

                if (grid[at] == 1) {
                    regions++
                    grid.fillRegion(at)
                }
            }
        }

        return regions
    }

    private fun Grid.fillRegion(at: Coordinate) {
        if (this[at] == 0) {
            return
        }

        val queue: Queue<Coordinate> = LinkedList()
        queue.add(at)

        while (queue.isNotEmpty()) {
            val current = queue.remove()
            this[current] = 0

            current.adjacent().forEach {
                if (this[it] == 1) {
                    queue.add(it)
                }
            }
        }
    }

    private fun input() = "jzgqcdpd"
}