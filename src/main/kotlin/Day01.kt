package org.cesarbs.aoc2017.day01

object Day01 {
    fun solvePart1(input: String): Int = solvePart1(stringToDigitSequence(input))

    fun solvePart1(input: List<Int>): Int = solve(input, 1)

    fun solvePart2(input: String): Int = solvePart2(stringToDigitSequence(input))

    fun solvePart2(input: List<Int>): Int = solve(input, input.size / 2)

    private fun solve(input: List<Int>, next: Int): Int =
        input.indices
            .filter { input[it] == input[(it + next) % input.size] }
            .map { input[it] }
            .sum()

    private fun stringToDigitSequence(input: String) = input.map { it.toInt() - '0'.toInt() }
}