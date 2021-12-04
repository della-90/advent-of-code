package adventofcode

abstract class AbstractPuzzle(protected val input: String) {
    abstract fun solvePart1(): String
    abstract fun solvePart2(): String
}