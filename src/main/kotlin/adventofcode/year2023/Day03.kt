package adventofcode.year2023

import adventofcode.AbstractPuzzle

class Day03(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val engineSchematic = input
            .lines()
            .map { it.toCharArray() }
            .toTypedArray()

        var result = 0

        val directions = listOf(
            Pair(-1, -1),
            Pair(0, -1),
            Pair(1, -1),
            Pair(-1, 0),
            Pair(1, 0),
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, 1),
        )

        for (y in engineSchematic.indices) {
            for (x in engineSchematic[y].indices) {
                if (engineSchematic[y][x].isDigit() || engineSchematic[y][x] == '.') continue
                val directionsWithDigits = directions
                    .filter { (deltaY, deltaX) ->
                        (y + deltaY) in engineSchematic.indices
                                && (x + deltaX) in engineSchematic[y].indices
                                && engineSchematic[y + deltaY][x + deltaX].isDigit()
                    }

                // remove diagonals if there is one at the centre already
                val dedupedDirections = directionsWithDigits
                    .filter { (deltaY, deltaX) ->
                        deltaX == 0 || Pair(deltaY, 0) !in directionsWithDigits
                    }
                dedupedDirections.forEach { (deltaY, deltaX) ->
                    val number = findNumber(y + deltaY, x + deltaX, engineSchematic)
                    result += number
                }
            }
        }

        return result.toString()
    }

    override fun solvePart2(): String {
        val engineSchematic = input
            .lines()
            .map { it.toCharArray() }
            .toTypedArray()

        val directions = listOf(
            Pair(-1, -1),
            Pair(0, -1),
            Pair(1, -1),
            Pair(-1, 0),
            Pair(1, 0),
            Pair(-1, 1),
            Pair(0, 1),
            Pair(1, 1),
        )

        val locationOfPotentialGears = engineSchematic
            .indices
            .flatMap { y ->
                engineSchematic[y].indices.mapNotNull { x ->
                    if (engineSchematic[y][x] == '*')
                        Pair(y, x)
                    else
                        null
                }
            }
        val locationOfGears = locationOfPotentialGears
            .map { (y, x) ->
                val directionsWithDigits = directions
                    .filter { (deltaY, deltaX) ->
                        (y + deltaY) in engineSchematic.indices
                                && (x + deltaX) in engineSchematic[y].indices
                                && engineSchematic[y + deltaY][x + deltaX].isDigit()
                    }

                // remove diagonals if there is one at the centre already
                val dedupedDirections = directionsWithDigits
                    .filter { (deltaY, deltaX) ->
                        deltaX == 0 || Pair(deltaY, 0) !in directionsWithDigits
                    }

                val gearNumbers = dedupedDirections
                    .map { (deltaY, deltaX) ->
                        val number = findNumber(y + deltaY, x + deltaX, engineSchematic)
                        number
                    }

                gearNumbers
            }
            .filter { it.size == 2 }
        return locationOfGears
            .map { it.first() * it[1] }
            .sum()
            .toString()
    }

    private fun findNumber(y: Int, x: Int, engineSchematic: Array<CharArray>): Int {
        var startIndex = x
        while (startIndex >= 0 && engineSchematic[y][startIndex].isDigit()) {
            startIndex--
        }
        startIndex++
        var endIndex = x
        while (endIndex <= engineSchematic[y].lastIndex && engineSchematic[y][endIndex].isDigit()) {
            endIndex++
        }

        return engineSchematic[y].slice(startIndex until endIndex).joinToString("").toInt()
    }
}