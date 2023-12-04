package adventofcode.year2023

import adventofcode.AbstractPuzzle

class Day02(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val games = parseInput()

        return games
            .filter { (_, rounds) ->
                rounds.all { round ->
                    round.getOrDefault("red", 0) <= 12 &&
                            round.getOrDefault("green", 0) <= 13 &&
                            round.getOrDefault("blue", 0) <= 14
                }
            }
            .sumOf { it.first }
            .toString()
    }

    override fun solvePart2(): String {
        val games = parseInput()

        return games
            .map { (_, rounds) ->
                val blue = rounds.maxOf { it.getOrDefault("blue", 0) }
                val green = rounds.maxOf { it.getOrDefault("green", 0) }
                val red = rounds.maxOf { it.getOrDefault("red", 0) }
                blue * green * red
            }
            .sum()
            .toString()
    }

    private fun parseInput() = input
        .lines()
        .map { line ->
            val (game, rawRounds) = line.split(": ")
            val gameId = game.drop(5).toInt()
            val rounds = rawRounds
                .split("; ")
                .map { cubes ->
                    cubes
                        .split(", ")
                        .associate { round ->
                            val (number, colour) = round.split(" ")
                            colour to number.toInt()
                        }
                }

            gameId to rounds
        }
}