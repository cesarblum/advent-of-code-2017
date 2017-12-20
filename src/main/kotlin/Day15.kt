package org.cesarbs.aoc2017.day15

object Day15 {
    class Generator(initialValue: Long, private val factor: Long, private val predicate: (Long) -> Boolean) {
        constructor(initialValue: Long, factor: Long): this(initialValue, factor, { true })

        var previousValue = initialValue

        fun generate(): Long {
            while (true) {
                val value = (previousValue * factor) % 2147483647
                previousValue = value

                if (predicate(value)) {
                    return value
                }
            }
        }
    }

    fun solvePart1() = countMatches(input.first, input.second)

    fun solvePart2() = countMatchesWithCriteria(input.first, input.second)

    fun countMatches(initialValueA: Long, initialValueB: Long): Int =
        countMatches(
            Generator(initialValueA, 16807),
            Generator(initialValueB, 48271),
            40000000)

    fun countMatchesWithCriteria(initialValueA: Long, initialValueB: Long): Int =
        countMatches(
            Generator(initialValueA, 16807, { it % 4 == 0L }),
            Generator(initialValueB, 48271, { it % 8 == 0L }),
            5000000)

    private fun countMatches(generatorA: Generator, generatorB: Generator, pairs: Int): Int =
        0.until(pairs).count { (generatorA.generate() and 0xffff) == (generatorB.generate() and 0xffff) }

    val input = Pair(116L, 299L)
}