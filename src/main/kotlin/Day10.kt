package org.cesarbs.aoc2017.day10

object Day10 {
    data class HashRoundResult(val numbers: MutableList<Int>, val position: Int, val skipSize: Int)

    fun solvePart1() = hash1(256, input()).let { it[0] * it[1] }

    fun solvePart2() = hash2(256, input())

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

    fun hash1(listLength: Int, lengths: String): List<Int> =
        lengths.split(",").map { it.toInt() }.let { adaptedLengths ->
            hashRound(0.until(listLength).toMutableList(), adaptedLengths, 0, 0).numbers
        }

    fun hash2(listLength: Int, lengths: String): String {
        val adaptedLengths = lengths.map { it.toInt() }.plus(listOf(17, 31, 73, 47, 23))
        var roundResult = HashRoundResult(0.until(listLength).toMutableList(), 0, 0)

        for (round in 0..63) {
            roundResult = hashRound(roundResult.numbers, adaptedLengths, roundResult.position, roundResult.skipSize)
        }

        return roundResult.numbers
            .chunked(16, { block -> block.fold(0, Int::xor ) })
            .fold("", { denseHash, blockHash -> denseHash + String.format("%02x", blockHash) })
    }

    private fun hashRound(numbers: MutableList<Int>,
                          lengths: List<Int>,
                          initialPosition: Int,
                          initialSkipSize: Int): HashRoundResult {
        var position = initialPosition
        var skipSize = initialSkipSize

        lengths.forEach { length ->
            numbers.reverseCircular(position, length)
            position = (position + length + skipSize) % numbers.size
            skipSize++
        }

        return HashRoundResult(numbers, position, skipSize)
    }

    private fun input() = "83,0,193,1,254,237,187,40,88,27,2,255,149,29,42,100"
}