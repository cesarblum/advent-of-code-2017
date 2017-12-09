package org.cesarbs.aoc2017.day02

object Day02 {
    fun solvePart01(input: String): Int = solve(input, ::checksumPart01)

    fun solvePart02(input: String): Int = solve(input, ::checksumPart02)

    fun checksumPart01(spreadsheet: List<List<Int>>): Int =
        checksum(spreadsheet, ::findMaxDifference)

    fun checksumPart02(spreadsheet: List<List<Int>>): Int =
        checksum(spreadsheet, ::findEvenDivision)

    private fun solve(input: String, checksum: (List<List<Int>>) -> Int): Int = checksum(parseInput(input))

    private fun checksum(spreadsheet: List<List<Int>>, rowChecksum: (List<Int>) -> Int): Int =
        spreadsheet.fold(0, { sum, row -> sum + rowChecksum(row) })

    private fun findMaxDifference(row: List<Int>): Int = row.max()!! - row.min()!!

    private fun findEvenDivision(row: List<Int>): Int =
        row.flatMap { x -> row.minus(x).map { y -> Pair(x, y) } }
            .filter { it.first >= it.second }
            .first { it.first % it.second == 0 }
            .let { it.first / it.second }

    private fun parseInput(input: String): List<List<Int>> = input.lines()
        .filter { it.isNotEmpty() }
        .map { it.split("\t").map { it.toInt() } }
}