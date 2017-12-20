package org.cesarbs.aoc2017.day10

object Day10 {
    data class HashRoundResult(val numbers: MutableList<Int>, val position: Int, val skipSize: Int)

    fun solvePart1() = singleRoundHash(256, input()).let { it[0] * it[1] }

    fun solvePart2() = knotHash(256, input())

    private fun <T> MutableList<T>.reverseCircular(fromIndex: Int, length: Int) {
        var i = fromIndex
        var j = (fromIndex + length - 1) % this.size

        0.until(length / 2).forEach {
            val tmp = this[i]
            this[i] = this[j]
            this[j] = tmp

            i = (i + 1) % this.size
            j = if (j == 0) this.size - 1 else j - 1
        }
    }

    fun singleRoundHash(listLength: Int, input: String): List<Int> =
        input.split(",").map { it.toInt() }.let { parsedInput ->
            hashRound(0.until(listLength).toMutableList(), parsedInput, 0, 0).numbers
        }

    fun knotHash(listLength: Int, input: String): String {
        val adaptedInput = input.map { it.toInt() }.plus(listOf(17, 31, 73, 47, 23))
        var roundResult = HashRoundResult(0.until(listLength).toMutableList(), 0, 0)

        for (round in 0..63) {
            roundResult = hashRound(roundResult.numbers, adaptedInput, roundResult.position, roundResult.skipSize)
        }

        return roundResult.numbers
            .chunked(16, { block -> block.fold(0, Int::xor ) })
            .fold("", { denseHash, blockHash -> denseHash + String.format("%02x", blockHash) })
    }

    private fun hashRound(numbers: MutableList<Int>,
                          input: List<Int>,
                          initialPosition: Int,
                          initialSkipSize: Int): HashRoundResult {
        var position = initialPosition
        var skipSize = initialSkipSize

        input.forEach {
            numbers.reverseCircular(position, it)
            position = (position + it + skipSize) % numbers.size
            skipSize++
        }

        return HashRoundResult(numbers, position, skipSize)
    }

    private fun input() = "83,0,193,1,254,237,187,40,88,27,2,255,149,29,42,100"
}