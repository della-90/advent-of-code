package adventofcode.year2021

import adventofcode.AbstractPuzzle

class Day04(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val numbers = input.lines().first().split(",").map { it.toInt() }
        val boards = input
                .lines()
                .drop(1)
                .filter { it.isNotEmpty() }
                .chunked(5)
                .map(this::parseBoard)

        val result = numbers
                .flatMap { number ->
                    boards.map { board ->
                        board.draw(number)
                        if (board.hasWon) {
                            board.unmatchedNumbers.sum() * number
                        } else {
                            null
                        }
                    }
                }
                .filterNotNull()
                .first()

        return result.toString()
    }

    override fun solvePart2(): String {
        val numbers = input.lines().first().split(",").map { it.toInt() }
        val boards = input
                .lines()
                .drop(1)
                .filter { it.isNotEmpty() }
                .chunked(5)
                .map(this::parseBoard)

        val result = numbers
                .flatMap { number ->
                    boards
                            .filterNot { it.hasWon }
                            .map { board ->
                                board.draw(number)
                                if (board.hasWon) {
                                    board.unmatchedNumbers.sum() * number
                                } else {
                                    null
                                }
                            }
                }
                .filterNotNull()
                .last()

        return result.toString()
    }

    private fun parseBoard(rawInput: List<String>): BingoBoard {
        require(rawInput.size == 5)
        val cells = rawInput.map { line ->
            line
                    .split(" ")
                    .filter { it.isNotEmpty() }
                    .map { BingoBoardCell(it.toInt()) }
        }

        return BingoBoard(cells)
    }

    private data class BingoBoardCell(val number: Int, var isMatched: Boolean = false)

    private data class BingoBoard(val cells: List<List<BingoBoardCell>>) {
        val hasWon: Boolean
            get() = isRowMatched || isColumnMatched

        private val isRowMatched: Boolean
            get() = cells.any { row ->
                row.all { it.isMatched }
            }

        private val isColumnMatched: Boolean
            get() = (0..4).any { columnIndex ->
                cells.all { row ->
                    row[columnIndex].isMatched
                }
            }

        val unmatchedNumbers: List<Int>
            get() = cells
                    .flatten()
                    .filter { !it.isMatched }
                    .map { it.number }

        fun draw(number: Int) {
            cells
                    .flatten()
                    .firstOrNull { it.number == number }
                    ?.isMatched = true
        }
    }
}
