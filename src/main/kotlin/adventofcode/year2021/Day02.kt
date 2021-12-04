package adventofcode.year2021

import adventofcode.AbstractPuzzle
import adventofcode.Point2D

class Day02(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val finalPosition = input
                .lines()
                .map(Command::from)
                .fold(Point2D(0, 0)) { acc, command ->
                    when (command) {
                        is Command.Down -> Point2D(acc.x, acc.y + command.amount)
                        is Command.Forward -> Point2D(acc.x + command.amount, acc.y)
                        is Command.Up -> Point2D(acc.x, acc.y - command.amount)
                    }
                }

        return (finalPosition.x * finalPosition.y).toString()
    }

    override fun solvePart2(): String {
        var aim = 0
        val finalPosition = input
                .lines()
                .map(Command::from)
                .fold(Point2D(0, 0)) { acc, command ->
                    when (command) {
                        is Command.Down -> {
                            aim += command.amount
                            acc
                        }
                        is Command.Up -> {
                            aim -= command.amount
                            acc
                        }
                        is Command.Forward -> Point2D(acc.x + command.amount, acc.y + aim * command.amount)
                    }
                }

        return (finalPosition.x * finalPosition.y).toString()
    }

    private sealed class Command(val amount: Int) {
        companion object {
            fun from(raw: String): Command {
                val (direction, unit) = raw.split(" ")
                return when (direction) {
                    "down" -> Down(unit.toInt())
                    "up" -> Up(unit.toInt())
                    "forward" -> Forward(unit.toInt())
                    else -> throw IllegalArgumentException("Unknown direction: $direction")
                }
            }
        }

        class Forward(amount: Int) : Command(amount)
        class Up(amount: Int) : Command(amount)
        class Down(amount: Int) : Command(amount)
    }
}