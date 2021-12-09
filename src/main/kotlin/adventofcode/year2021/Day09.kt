package adventofcode.year2021

import adventofcode.AbstractPuzzle
import adventofcode.Point2D

class Day09(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val floor = input
            .lines()
            .map { line -> line.toList().map { it.digitToInt() }}

        val adjacentDirections = listOf(
            Point2D(0, 1),
            Point2D(0, -1),
            Point2D(1, 0),
            Point2D(-1, 0)
        )

        var result = 0
        for (y in floor.indices) {
            for (x in floor[y].indices) {
                val currentPoint = Point2D(x, y)

                val neighbours = adjacentDirections
                    .map { it + currentPoint }
                    .filter { it.x in floor[y].indices && it.y in floor.indices}

                if (neighbours.all { floor[it.y][it.x] > floor[y][x] }) {
                    result += floor[y][x] + 1
                }
            }
        }
        return result.toString()
    }

    override fun solvePart2(): String {
        val floor = input
            .lines()
            .map { line -> line.toList().map { it.digitToInt() }}

        val adjacentDirections = listOf(
            Point2D(0, 1),
            Point2D(0, -1),
            Point2D(1, 0),
            Point2D(-1, 0)
        )

        val basins = mutableSetOf(setOf<Point2D>())
        for (y in floor.indices) {
            for (x in floor[y].indices) {
                if (floor[y][x] != 9) {
                    val currentPoint = Point2D(x, y)

                    // find adjacent basins
                    val adjacentPoints = adjacentDirections
                        .map { it + currentPoint }
                        .filter { it.y in floor.indices && it.x in floor[y].indices }

                    val adjacentBasins = basins.filter { basin ->
                        adjacentPoints.any { it in basin }
                    }

                    // merge all adjacent basins thanks to the current cell
                    val newBasin = adjacentBasins.fold(emptySet<Point2D>()) { acc, basin ->
                        basins.remove(basin)
                        acc + basin
                    } + currentPoint

                    basins.add(newBasin)
                }
            }
        }

        return basins
            .sortedByDescending { it.size }
            .take(3)
            .fold(1) { acc, basin -> acc * basin.size }
            .toString()
    }
}
