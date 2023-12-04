package adventofcode.year2023

import adventofcode.AbstractPuzzle
import java.util.*
import java.util.stream.Collectors
import kotlin.math.pow

class Day04(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        return input
            .lines()
            .map { Game.parse(it) }
            .sumOf { it.score }
            .toString()
    }

    override fun solvePart2(): String {
        val initialGames = input
            .lines()
            .map { Game.parse(it) }
            .associateBy { it.cardId }

        val amountOfCards = initialGames.values.associateWith { 1 }.toMutableMap()

        var result = amountOfCards.values.sum()
        while (amountOfCards.values.any { it > 0 }) {
            val card = amountOfCards.entries.first { it.value > 0 }.key
            val amountOfCard = amountOfCards.getValue(card)
            amountOfCards[card] = 0
            val countOfMatchingNumbers = (card.winningNumbers intersect card.myNumbers).size
            repeat(countOfMatchingNumbers) {
                val nextCard = initialGames.getValue(card.cardId + it + 1)
                amountOfCards[nextCard] = amountOfCards.getValue(nextCard) + amountOfCard
            }

            result += countOfMatchingNumbers * amountOfCard
        }
        return result.toString()
    }

    private data class Game(val cardId: Int, val winningNumbers: Set<Int>, val myNumbers: Set<Int>) {
        companion object {
            fun parse(raw: String): Game {
                val indexOfColonSeparator = raw.indexOf(':')
                val indexOfNumberSeparator = raw.indexOf('|')

                val cardId = raw.substring(0, indexOfColonSeparator).dropWhile { !it.isDigit() }.toInt()
                val winningNumbers = Scanner(raw.substring(indexOfColonSeparator + 1, indexOfNumberSeparator))
                    .findAll("\\d+")
                    .map { it.group().toInt() }
                    .collect(Collectors.toSet())

                val myNumbers = Scanner(raw.substring(indexOfNumberSeparator + 1))
                    .findAll("\\d+")
                    .map { it.group().toInt() }
                    .collect(Collectors.toSet())

                return Game(cardId, winningNumbers, myNumbers)
            }
        }

        val score: Int
            get() {
                val matchingNumbers = (winningNumbers intersect myNumbers).size

                return if (matchingNumbers == 0) 0 else 2.0.pow(matchingNumbers - 1).toInt()
            }
    }
}