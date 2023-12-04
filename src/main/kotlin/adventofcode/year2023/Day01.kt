package adventofcode.year2023

import adventofcode.AbstractPuzzle

class Day01(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        return input
            .lines()
            .sumOf { line ->
                val digits = line
                    .filter { it.isDigit() }
                    .map { it.digitToInt() }

                digits.first() * 10 + digits.last()
            }
            .toString()
    }

    override fun solvePart2(): String {
        val numbers = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        return input
            .lines()
            .map { line ->
                var result = line
                var i = 0
                while (i in result.indices) {
                    val replacement = numbers.entries.find { (key, _) ->
                        i + key.length <= result.length && result.substring(i, i + key.length) == key
                    }

                    if (replacement != null) {
                        result = result.replaceRange(i, i + replacement.key.length - 1, replacement.value.toString())
                    }
                    i++
                }

                result
            }
            .sumOf { line ->
                val digits = line
                    .filter { it.isDigit() }
                    .map { it.digitToInt() }

                digits.first() * 10 + digits.last()
            }
            .toString()
    }
}