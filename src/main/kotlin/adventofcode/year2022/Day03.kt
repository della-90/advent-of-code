package adventofcode.year2022

import adventofcode.AbstractPuzzle

class Day03(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val ruckSacks = input.lines()

        return ruckSacks
            .map { items ->
                val length = items.length
                val firstCompartment = items.take(length / 2)
                val secondCompartment = items.drop(length / 2)

                firstCompartment to secondCompartment
            }
            .map { (firstCompartment, secondCompartment) ->
                findCommon(firstCompartment, secondCompartment)
            }
            .sumOf { common ->
                computePriority(common)
            }
            .toString()
    }

    override fun solvePart2(): String {
        val ruckSacks = input.lines()

        val groups = ruckSacks.chunked(3)

        return groups
            .map { group ->
                group.map { it.toSet() }.reduce { acc, chars -> acc intersect chars}.first()
            }
            .sumOf { computePriority(it) }
            .toString()
    }

    private fun findCommon(firstCompartment: String, secondCompartment: String) =
        (firstCompartment.toSet() intersect secondCompartment.toSet()).first()

    private fun computePriority(common: Char): Int {
        val lowercase = 'a'..'z'
        val uppercase = 'A'..'Z'

        return if (common in lowercase) lowercase.indexOf(common) + 1 else uppercase.indexOf(common) + 27
    }
}