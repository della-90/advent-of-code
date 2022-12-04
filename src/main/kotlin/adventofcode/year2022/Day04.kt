package adventofcode.year2022

import adventofcode.AbstractPuzzle

class Day04(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val pairs = input
            .lines()
            .map { it.split(",") }
            .map { (first, second) ->
                parseRange(first) to parseRange(second)
            }

        val included = pairs
            .count { (first, second) ->
                first in second || second in first
            }

        return included.toString()
    }

    override fun solvePart2(): String {
        val pairs = input
            .lines()
            .map { it.split(",") }
            .map { (first, second) ->
                parseRange(first) to parseRange(second)
            }

        val overlapped = pairs
            .count { (first, second) ->
                first intersects second || second intersects first
            }

        return overlapped.toString()
    }

    private operator fun IntRange.contains(other: IntRange): Boolean {
        return other.first in this && other.last in this
    }

    private infix fun IntRange.intersects(other: IntRange): Boolean {
        return this.first in other || this.last in other
    }

    private fun parseRange(s: String): IntRange {
        val (beginning, end) = s.split("-")
        return beginning.toInt()..end.toInt()
    }
}