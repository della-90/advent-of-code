package adventofcode.year2022

import adventofcode.AbstractPuzzle

class Day02(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val games = input
            .lines()
            .map { it.split(" ").map { symbol -> Shape.fromSymbol(symbol) } }

            return games
                .fold(0) { acc, (opponent, me) ->
                    acc + me.score + getGameScore(me, opponent)
                }
                .toString()
    }

    override fun solvePart2(): String {
        val games = input
            .lines()
            .map { it.split(" ") }
            .map { (opponent, outcome) -> Shape.fromSymbol(opponent) to GameOutcome.fromSymbol(outcome) }

        return games
            .fold(0) { acc, (opponent, outcome) ->
                acc + outcome.score + getDesiredShape(opponent, outcome).score
            }
            .toString()
    }

    private fun getGameScore(me: Shape, other: Shape): Int {
        return when {
            me == other -> 3
            me.beats == other -> 6
            else -> 0
        }
    }

    private fun getDesiredShape(opponent: Shape, desiredOutcome: GameOutcome): Shape {
        return when(desiredOutcome) {
            GameOutcome.LOSE -> opponent.beats
            GameOutcome.DRAW -> opponent
            GameOutcome.WIN -> Shape.values().first { it.beats == opponent }
        }
    }

    private enum class GameOutcome(val score: Int) {
        LOSE(0),
        DRAW(3),
        WIN(6);

        companion object {
            fun fromSymbol(symbol: String) = when(symbol) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw IllegalArgumentException("Unknown outcome $symbol")
            }
        }
    }

    private enum class Shape(val score: Int) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        val beats: Shape
            get() = when (this) {
                ROCK -> SCISSORS
                PAPER -> ROCK
                SCISSORS -> PAPER
            }

        companion object {
            fun fromSymbol(symbol: String): Shape = when (symbol) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> throw IllegalArgumentException("Unknown symbol $symbol")
            }
        }
    }
}