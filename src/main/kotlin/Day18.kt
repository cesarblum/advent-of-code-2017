package org.cesarbs.aoc2017.day18

import java.util.*

object Day18 {
    enum class Opcode {
        SND,
        SET,
        ADD,
        MUL,
        MOD,
        RCV,
        JGZ
    }

    data class Instruction(val opcode: Opcode, val operand1: Any, val operand2: Any? = null)

    class SoundProcessor {
        private val registers: MutableMap<Char, Long> = HashMap()
        private var lastFrequency: Long? = null

        fun execute(program: List<Instruction>, rcvCallback: (Long) -> Boolean) {
            var ip = 0L
            var jumped = false

            loop@ while (true) {
                if (ip < 0 || ip >= program.size) {
                    break
                }

                val instruction = program[ip.toInt()]

                when (instruction.opcode) {
                    Opcode.SND -> snd(decode(instruction.operand1))
                    Opcode.SET -> set(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.ADD -> add(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.MUL -> mul(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.MOD -> mod(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.RCV -> if (rcv(decode(instruction.operand1), rcvCallback)) break@loop
                    Opcode.JGZ -> if (decode(instruction.operand1) > 0) {
                        ip += decode(instruction.operand2!!)
                        jumped = true
                    }
                }

                if (!jumped) {
                    ip++
                }

                jumped = false
            }
        }

        private fun snd(frequency: Long) {
            lastFrequency = frequency
        }

        private fun set(register: Char, value: Long) {
            registers[register] = value
        }

        private fun add(register: Char, value: Long) {
            registers[register] = registers[register]?.plus(value) ?: value
        }

        private fun mul(register: Char, value: Long) {
            registers[register] = registers[register]?.times(value) ?: 0L
        }

        private fun mod(register: Char, value: Long) {
            registers[register] = registers[register]?.rem(value) ?: 0L
        }

        private fun rcv(comparand: Long, rcvCallback: (Long) -> Boolean): Boolean =
            comparand != 0L && rcvCallback(lastFrequency!!)

        private fun decode(operand: Any): Long =
            when (operand) {
                is Char -> registers[operand] ?: 0L
                is Long -> operand
                else -> throw IllegalArgumentException()
            }
    }

    class ParallelProcessor {
        private val registers: Array<MutableMap<Char, Long>> = arrayOf(HashMap(), HashMap())

        init {
            registers[0]['p'] = 0L
            registers[1]['p'] = 1L
        }

        private val queue: Array<Queue<Long>> = arrayOf(ArrayDeque(), ArrayDeque())

        private var current: Int = 0

        fun execute(program: List<Instruction>, sndCallback: (Int) -> Unit = {}) {
            var ip = arrayOf(0L, 0L)
            var jumped = false
            var switch = false

            loop@ while (true) {
                if (ip[current] < 0 || ip[current] >= program.size) {
                    queue[current].clear()
                    current = other()
                }

                if ((ip[current] < 0 || ip[current] >= program.size) &&
                    (ip[other()] < 0 || ip[other()] >= program.size)) {
                    break
                }

                val instruction = program[ip[current].toInt()]

                when (instruction.opcode) {
                    Opcode.SND -> snd(decode(instruction.operand1), sndCallback)
                    Opcode.SET -> set(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.ADD -> add(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.MUL -> mul(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.MOD -> mod(instruction.operand1 as Char, decode(instruction.operand2!!))
                    Opcode.RCV -> if (rcv(instruction.operand1 as Char)) {
                        switch = true
                    }
                    Opcode.JGZ -> if (decode(instruction.operand1) > 0) {
                        ip[current] += decode(instruction.operand2!!)
                        jumped = true
                    }
                }

                if (!jumped && !switch) {
                    ip[current]++
                }

                jumped = false

                if (switch) {
                    if (queue[other()].size == 0) {
                        // Deadlock
                        break@loop
                    }

                    current = other()
                    switch = false
                }
            }
        }

        private fun other() = current xor 1

        private fun snd(value: Long, sndCallback: (Int) -> Unit) {
            queue[other()].add(value)
            sndCallback(current)
        }

        private fun set(register: Char, value: Long) {
            registers[current][register] = value
        }

        private fun add(register: Char, value: Long) {
            registers[current][register] = registers[current][register]?.plus(value) ?: value
        }

        private fun mul(register: Char, value: Long) {
            registers[current][register] = registers[current][register]?.times(value) ?: 0L
        }

        private fun mod(register: Char, value: Long) {
            registers[current][register] = registers[current][register]?.rem(value) ?: 0L
        }

        private fun rcv(register: Char): Boolean {
            if (queue[current].size > 0) {
                registers[current][register] = queue[current].remove()
                return false
            }

            return true
        }

        private fun decode(operand: Any): Long =
            when (operand) {
                is Char -> registers[current][operand] ?: 0L
                is Long -> operand
                else -> throw IllegalArgumentException()
            }
    }

    fun solvePart1(): Long = firstRecoveredFrequency(input())

    fun solvePart2(): Long = countSndCalls(input(), 1)

    fun firstRecoveredFrequency(program: String): Long = firstRecoveredFrequency(parseProgram(program))

    private fun firstRecoveredFrequency(program: List<Instruction>): Long {
        var recoveredFrequency: Long? = null

        SoundProcessor().execute(program, { frequency ->
            recoveredFrequency = frequency
            true
        })

        return recoveredFrequency!!
    }

    fun countSndCalls(program: String, programId: Int): Long = countSndCalls(parseProgram(program), programId)

    private fun countSndCalls(program: List<Instruction>, programId: Int): Long {
        var sndCalls = 0L

        ParallelProcessor().execute(program, { id ->
            if (id == programId) {
                sndCalls++
            }
        })

        return sndCalls
    }

    fun parseProgram(program: String): List<Instruction> = program.lines()
        .map { it.split(" ") }
        .map { line ->
            when (line[0]) {
                "snd" -> Instruction(Opcode.SND, parseLongOrChar(line[1]))
                "set" -> Instruction(Opcode.SET, line[1][0], parseLongOrChar(line[2]))
                "add" -> Instruction(Opcode.ADD, line[1][0], parseLongOrChar(line[2]))
                "mul" -> Instruction(Opcode.MUL, line[1][0], parseLongOrChar(line[2]))
                "mod" -> Instruction(Opcode.MOD, line[1][0], parseLongOrChar(line[2]))
                "rcv" -> Instruction(Opcode.RCV, parseLongOrChar(line[1]))
                "jgz" -> Instruction(Opcode.JGZ, parseLongOrChar(line[1]), parseLongOrChar(line[2]))
                else -> throw IllegalArgumentException()
            }
        }

    private fun parseLongOrChar(input: String): Any =
        if (input.matches(Regex("[a-z]"))) {
            input[0]
        } else {
            input.toLong()
        }

    private fun input() = """set i 31
set a 1
mul p 17
jgz p p
mul a 2
add i -1
jgz i -2
add a -1
set i 127
set p 618
mul p 8505
mod p a
mul p 129749
add p 12345
mod p a
set b p
mod b 10000
snd b
add i -1
jgz i -9
jgz a 3
rcv b
jgz b -1
set f 0
set i 126
rcv a
rcv b
set p a
mul p -1
add p b
jgz p 4
snd a
set a b
jgz 1 3
snd b
set f 1
add i -1
jgz i -11
snd a
jgz f -16
jgz a -19"""
}