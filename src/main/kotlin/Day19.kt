package org.cesarbs.aoc2017.day19

import java.nio.file.Files
import java.nio.file.Paths

object Day19 {
    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    data class DiagramStats(val steps: Int, val charSequence: List<Char>)

    fun solvePart1(): String = followDiagram(input()).charSequence.joinToString("")

    fun solvePart2(): Int = followDiagram(input()).steps

    fun followDiagram(diagram: List<String>): DiagramStats {
        val charSequence = mutableListOf<Char>()
        var steps = 0
        var col = diagram.first().indexOf('|')
        var row = 0
        var direction = Direction.DOWN

        while (true) {
            when (diagram[row][col]) {
                in 'A'..'Z' -> charSequence.add(diagram[row][col])
                '+' -> direction = turn(diagram, row, col, direction)
                ' ' -> return DiagramStats(steps, charSequence)
            }

            when (direction) {
                Direction.UP -> row--
                Direction.DOWN -> row++
                Direction.LEFT -> col--
                Direction.RIGHT -> col++
            }

            steps++
        }
    }

    private fun turn(diagram: List<String>, row: Int, col: Int, currentDirection: Direction): Direction {
        var direction = currentDirection

        if (diagram[row][col] == '+') {
            if (direction == Direction.UP || direction == Direction.DOWN) {
                if (diagram[row][col - 1] == ' ') {
                    direction = Direction.RIGHT
                } else {
                    direction = Direction.LEFT
                }
            } else {
                if (diagram[row - 1][col] == ' ') {
                    direction = Direction.DOWN
                } else {
                    direction = Direction.UP
                }
            }
        }

        return direction
    }

    private fun input(): List<String> =
        Files.readAllLines(Paths.get(javaClass.classLoader.getResource("day19input.txt").file))
}