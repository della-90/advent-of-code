package adventofcode.year2021

import adventofcode.AbstractPuzzle

class Day01(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val scans = input
                .lines()
                .map { it.toInt() }

        return scans
                .windowed(2)
                .count { (first, second) -> second > first }
                .toString()
    }

    override fun solvePart2(): String {
        val scans = input
                .lines()
                .map { it.toInt() }

        return scans
                .windowed(3)
                .map { it.sum() }
                .windowed(2)
                .count { (first, second) -> second > first }
                .toString()
    }
}