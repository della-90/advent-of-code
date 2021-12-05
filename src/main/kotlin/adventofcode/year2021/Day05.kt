package adventofcode.year2021

import adventofcode.AbstractPuzzle
import adventofcode.Point2D

class Day05(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val vents = input
            .lines()
            .mapNotNull { line ->
                val (from, to) = line.split(" -> ")
                val (fromX, fromY) = from.split(",").map { it.toInt() }
                val (toX, toY) = to.split(",").map { it.toInt() }
                if (toX != fromX && toY != fromY)
                    null
                else
                    Segment(Point2D(fromX, fromY), Point2D(toX, toY))
            }

        val allPoints = vents
            .flatMap { it.getAllPoints() }
            .toList()
            .groupingBy { it }

        val result = allPoints.eachCount()
            .filterValues { it > 1 }
            .count()

        return result.toString()
    }

    override fun solvePart2(): String {
        val vents = input
            .lines()
            .map { line ->
                val (from, to) = line.split(" -> ")
                val (fromX, fromY) = from.split(",").map { it.toInt() }
                val (toX, toY) = to.split(",").map { it.toInt() }
                Segment(Point2D(fromX, fromY), Point2D(toX, toY))
            }

        val allPoints = vents
            .flatMap { it.getAllPoints() }
            .toList()
            .groupingBy { it }

        val result = allPoints.eachCount()
            .filterValues { it > 1 }
            .count()

        return result.toString()
    }

    private class Segment(val from: Point2D, val to: Point2D) {
        private enum class Direction {
            N, S, E, W, NE, NW, SE, SW
        }

        private val direction = if (to.x == from.x) {
            if (to.y > from.y) Direction.S else Direction.N
        } else if (to.y == from.y) {
            if (to.x > from.x) Direction.E else Direction.W
        } else if (to.y > from.y) {
            if (to.x > from.x) Direction.SE else Direction.SW
        } else {
            if (to.x > from.x) Direction.NE else Direction.NW
        }

        fun getAllPoints(): Sequence<Point2D> {
            return generateSequence(from) { currentPoint ->
                return@generateSequence if (currentPoint.x == to.x && currentPoint.y == to.y) {
                    null
                } else {
                    when (direction) {
                        Direction.N -> Point2D(currentPoint.x, currentPoint.y - 1)
                        Direction.S -> Point2D(currentPoint.x, currentPoint.y + 1)
                        Direction.E -> Point2D(currentPoint.x + 1, currentPoint.y)
                        Direction.W -> Point2D(currentPoint.x - 1, currentPoint.y)
                        Direction.NE -> Point2D(currentPoint.x + 1, currentPoint.y - 1)
                        Direction.NW -> Point2D(currentPoint.x - 1, currentPoint.y - 1)
                        Direction.SE -> Point2D(currentPoint.x + 1, currentPoint.y + 1)
                        Direction.SW -> Point2D(currentPoint.x - 1, currentPoint.y + 1)
                    }
                }
            }
        }
    }
}
