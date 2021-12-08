package adventofcode.year2021

import adventofcode.AbstractPuzzle

class Day08(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val digits = input
            .lines()
            .map { it.split(" | ")[1] }
            .flatMap { it.split(" ") }

        return digits
            .count { it.length in listOf(2, 3, 4, 7) }
            .toString()
    }

    override fun solvePart2(): String {
        return input
            .lines()
            .map { it.split(" | ") }
            .map { (wires, display) -> Pair(wires.split(" "), display.split(" ")) }
            .sumOf { (wires, display) ->
                val mappings = guessMappings(wires)
                val digits = display
                    .map { decodeNumber(mappings, it) }

                digits.reduce { acc, digit ->
                    acc * 10 + digit
                }
            }
            .toString()
    }

    private fun decodeNumber(wireMappings: Map<Int, Set<Char>>, segments: String): Int {
        val s = segments.toSet()
        return wireMappings
            .filterValues { it == s }
            .firstNotNullOf { it.key }
    }

    private fun guessMappings(wires: List<String>): Map<Int, Set<Char>> {
        /*
         * segments are:
         * a == top
         * b == top left
         * c == top right
         * d == centre
         * e == bottom left
         * f == bottom right
         * g == bottom
         */
        val one = wires.first { it.length == 2 }
        val seven = wires.first { it.length == 3 }
        val four = wires.first { it.length == 4 }
        val eight = wires.first { it.length == 7 }

        val c = one.minByOrNull { segment ->
            wires.count { segment in it }
        }!!
        val f = one.first { it != c }

        val two = wires.first { f !in it }
        val a = seven.first { it !in one }

        val five = wires
            .filter { it.length == 5 }
            .first { c !in it }

        val six = wires
            .filter { it.length == 6 }
            .first { c !in it }

        val three = wires
            .filter { it.length == 5 }
            .first { a in it && c in it && f in it }

        val e = two.first { it !in three }

        val nine = wires
            .filter { it.length == 6 }
            .first { e !in it }

        val zero = wires
            .filter { it.length == 6 }
            .first {
                it != six && it != nine
            }

        return listOf(zero, one, two, three, four, five, six, seven, eight, nine)
            .mapIndexed { index, segments -> index to segments.toSet() }
            .toMap()
    }
}
