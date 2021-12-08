package adventofcode.year2021

import adventofcode.AbstractPuzzle

class Day06(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        return runExperiment(80).toString()
    }

    override fun solvePart2(): String {
        return runExperiment(256).toString()
    }

    private fun runExperiment(days: Int): Long {
        val lanternfish = input.split(",").map { it.toLong() }

        val numberOfFishByDay = LongArray(9) { day ->
            lanternfish.count {it == day.toLong() }.toLong()
        }

        repeat(days) {
            val fishToSpawn = numberOfFishByDay[0]
            for (i in 1..numberOfFishByDay.lastIndex) {
                numberOfFishByDay[i - 1] = numberOfFishByDay[i]
            }
            numberOfFishByDay[6] += fishToSpawn
            numberOfFishByDay[8] = fishToSpawn
        }

        return numberOfFishByDay.sum()
    }
}
