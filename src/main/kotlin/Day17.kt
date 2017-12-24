package org.cesarbs.aoc2017.day17

import java.util.*

object Day17 {
    class CircularBufferIterator<E>(private val buffer: MutableList<E>) {
        private var iterator = buffer.listIterator()

        fun next(): E {
            if (!iterator.hasNext()) {
                iterator = buffer.listIterator()
            }

            return iterator.next()
        }

        fun add(value: E) {
            iterator.add(value)
        }
    }

    fun solvePart1() = findValueNextToLastWritten(2017, input)

    fun solvePart2() = findValueNextToZero(50000000, input)

    fun findValueNextToLastWritten(writeTo: Int, stepSize: Int): Int {
        val iterator = CircularBufferIterator(LinkedList(listOf((0))))

        for (value in 1..writeTo) {
            0.until(stepSize).forEach {
                iterator.next()
            }

            iterator.add(value)
        }

        return iterator.next()
    }

    private fun findValueNextToZero(writeTo: Int, stepSize: Int): Int {
        // 0 is the first value at the first position, and it never changes place
        // because values are always written after the current position. All we need
        // to do then is track values written whenever the current position is 0.
        var valueNextToZero: Int? = null

        (1..writeTo).fold(0, { previousPosition, value ->
            ((previousPosition + stepSize) % value + 1).also { currentPosition ->
                if (currentPosition == 1) {
                    valueNextToZero = value
                }
            }
        })

        return valueNextToZero!!
    }

    val input = 303
}