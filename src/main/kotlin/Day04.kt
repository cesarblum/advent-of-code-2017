package org.cesarbs.aoc2017.day04

object Day04 {
    fun solvePart1(input: String): Int = input.lines().count(this::hasUniqueWords)

    fun solvePart2(input: String): Int = input.lines().count(this::hasUniqueAnagrams)

    fun hasUniqueWords(passphrase: String): Boolean =
        passphrase
            .split(" ")
            .let { it.size == HashSet(it).size }

    fun hasUniqueAnagrams(passphrase: String): Boolean =
        passphrase
            .split(" ")
            .map { it.toCharArray().sorted() }
            .let { return it.size == HashSet(it).size }
}