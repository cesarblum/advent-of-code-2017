package org.cesarbs.aoc2017.day06

object Day06 {
    fun solvePart1() = solve { loopIteration, _ -> loopIteration }

    fun solvePart2() = solve { loopIteration, stateIteration -> loopIteration - stateIteration }

    private fun solve(processResult: (Int, Int) -> Int): Int = reallocationsUntilCycle(parseInput(input()), processResult)

    fun <T> reallocationsUntilCycle(initialMemory: List<Int>, processResult: (Int, Int) -> T): T {
        val reachedStates = HashMap<List<Int>, Int>()

        var memory = initialMemory
        var currentIteration = 0

        while (true) {
            val stateIteration = reachedStates.put(memory, currentIteration)

            if (stateIteration != null) {
                return processResult(currentIteration, stateIteration)
            }

            memory = reallocateMemory(memory)
            currentIteration++
        }
    }

    fun reallocateMemory(memory: List<Int>): List<Int> {
        val nextMemory = memory.toMutableList()
        val maxIndex = memory.indices.maxBy { memory[it] }!!
        var index = maxIndex
        var max = memory[maxIndex]

        nextMemory[index] = 0

        while (max > 0) {
            nextMemory[++index % memory.size]++
            max--
        }

        return nextMemory
    }

    private fun parseInput(input: String): List<Int> = input.split("\t").map(String::toInt)

    private fun input() = "14\t0\t15\t12\t11\t11\t3\t5\t1\t6\t8\t4\t9\t1\t8\t4"
}