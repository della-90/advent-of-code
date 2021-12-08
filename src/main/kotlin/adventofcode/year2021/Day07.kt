package adventofcode.year2021

import adventofcode.AbstractPuzzle
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.min

class Day07(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val positions = input
            .split(",")
            .map { it.toInt() }

        val median = positions.sorted()[positions.size / 2]

        return positions.fold(0) { acc, position ->
            acc + abs(position - median)
        }.toString()
    }

    override fun solvePart2(): String {
        val positions = input
            .split(",")
            .map { it.toInt() }

        val average = positions.average()

        val averageFloored = floor(average).toInt()
        val averageCeiled = ceil(average).toInt()

        var flooredCost = 0
        var ceiledCost = 0

        for (position in positions) {
            val flooredDistance = abs(averageFloored - position)
            flooredCost += (flooredDistance * (flooredDistance + 1)) / 2

            val ceiledDistance = abs(averageCeiled - position)
            ceiledCost += (ceiledDistance * (ceiledDistance + 1)) / 2
        }

        return min(flooredCost, ceiledCost).toString()
    }
}
