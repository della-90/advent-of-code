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
            .sumOf { (wires, display) ->
                val mappings = guessMappings(wires.split(" "))
                val digits = display
                    .split(" ")
                    .map { decodeNumber(mappings, it) }

                digits.reduce { acc, digit ->
                    acc * 10 + digit
                }
            }
            .toString()
    }

    private fun decodeNumber(mappings: Map<Int, Char>, segments: String): Int {
        val allSegments = mappings.values.toSet()
        return when (segments.length) {
            2 -> 1
            3 -> 7
            4 -> 4
            7 -> 8
            6 -> {
                val missingSegment = (allSegments - segments.toSet()).first()
                when (missingSegment) {
                    mappings.getValue(3) -> 0
                    mappings.getValue(2) -> 6
                    mappings.getValue(4) -> 9
                    else -> throw IllegalArgumentException()
                }
            }
            5 -> {
                val missingSegments = allSegments - segments.toSet()
                when (missingSegments) {
                    setOf(mappings.getValue(1), mappings.getValue(5)) -> 2
                    setOf(mappings.getValue(1), mappings.getValue(4)) -> 3
                    setOf(mappings.getValue(2), mappings.getValue(4)) -> 5
                    else -> throw IllegalArgumentException()
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun guessMappings(wires: List<String>): Map<Int, Char> {
        val wireMappings = hashMapOf<Int, Char>()
        val segmentsByNumber = hashMapOf<Int, Set<Char>>()

        val one = wires.first { it.length == 2 }
        segmentsByNumber[1] = one.toSet()

        // let's guess some random order for the two segments on the right. We'll fix them later
        wireMappings[2] = one.first()
        wireMappings[5] = one.last()

        val seven = wires.first { it.length == 3 }
        segmentsByNumber[7] = seven.toSet()
        wireMappings[0] = (segmentsByNumber.getValue(7) - segmentsByNumber.getValue(1)).first()

        val four = wires.first { it.length == 4 }
        segmentsByNumber[4] = four.toSet()

        // 9 is the only number that contains all segments in 7 and 4
        val nine = wires.first {
            it.toSet().containsAll(segmentsByNumber.getValue(7) + segmentsByNumber.getValue(4)) && it.length == 6
        }
        segmentsByNumber[9] = nine.toSet()

        // 8 contains all segments
        val eight = wires.first { it.length == 7 }
        segmentsByNumber[8] = eight.toSet()

        // the bottom left segment can be found subtracting 9 from 8
        wireMappings[4] = (segmentsByNumber.getValue(8) - segmentsByNumber.getValue(9)).first()

        // 0 contains all segments in 7 plus the bottom left segment
        val zero = wires.first {
            it.length == 6 && wireMappings[4] in it.toSet() && it.toSet().containsAll(segmentsByNumber.getValue(7))
        }
        segmentsByNumber[0] = zero.toSet()

        // 0 and 9 differ only in two segments: bottom left (already known) and centre
        wireMappings[3] = (segmentsByNumber.getValue(9) - segmentsByNumber.getValue(0)).first()

        // 3 is the only digit that has all segments in 7 plus centre, and has five segments in total
        val three = wires.first {
            it.length == 5 && it.toSet().containsAll(segmentsByNumber.getValue(7) + wireMappings[3])
        }
        segmentsByNumber[3] = three.toSet()
        wireMappings[6] = (segmentsByNumber.getValue(3) - wireMappings.values.toSet()).first()

        // the only segment left is top left
        wireMappings[1] = ("abcdefg".toSet() - wireMappings.values.toSet()).first()

        /*
         * Now we need to fix the segment on the right that we guessed at the beginning. We can use digit
         * 2 to find out what the bottom right segment is.
         *
         * To do that, we assume the original guess was right. If there is no digit that has a length of
         * five segments and contains the top right segment, then it means our original guess was wrong
         * and we have to swap the mappings.
         */

        val two = wires.firstOrNull {
            it.length == 5 && it.toSet().containsAll(segmentsByNumber.getValue(8) - wireMappings[1] - wireMappings[5])
        }
        if (two == null) {
            val tmp = wireMappings.getValue(2)
            wireMappings[2] = wireMappings.getValue(5)
            wireMappings[5] = tmp
        }

        return wireMappings
    }
}
