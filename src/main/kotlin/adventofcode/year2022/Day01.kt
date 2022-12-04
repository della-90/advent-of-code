package adventofcode.year2022

import adventofcode.AbstractPuzzle

class Day01(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val elves = parseInput(input)

        return elves.max().toString()
    }

    override fun solvePart2(): String {
        val elves = parseInput(input)
        return elves.sortedDescending().take(3).sum().toString()
    }

    private fun parseInput(input: String): List<Int> {
        val split = input.split(Regex("\\s{2,}"))
        return split
            .map {
                it
                    .lines()
                    .sumOf { line ->
                        line.toInt()
                    }
            }
    }
}