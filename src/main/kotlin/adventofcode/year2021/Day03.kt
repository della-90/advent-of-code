package adventofcode.year2021

import adventofcode.AbstractPuzzle

class Day03(input: String) : AbstractPuzzle(input) {
    override fun solvePart1(): String {
        val numbers = input
                .lines()
                .map(BitSet::fromString)

        val gammaRate = BitSet.fromString(
                (0 until numbers.first().size)
                        .map { mostCommonBit(it, numbers) }
                        .map { if (it) '1' else '0' }
                        .joinToString("")
        )

        val epsilonRate = gammaRate.flipBits()

        return (gammaRate.toInt() * epsilonRate.toInt()).toString()
    }

    override fun solvePart2(): String {
        val numbers = input
                .lines()
                .map(BitSet::fromString)

        val o2Number = findOneByCriteria(numbers) { n, index ->
            mostCommonBit(index, n)
        }

        val co2Number = findOneByCriteria(numbers) { n, index ->
            !mostCommonBit(index, n)
        }

        return (o2Number.toInt() * co2Number.toInt()).toString()
    }

    private fun mostCommonBit(index: Int, numbers: List<BitSet>): Boolean {
        val sumOfBits = numbers
                .map { it[index] }
                .fold(0) { acc, bit ->
                    acc + if (bit) 1 else 0
                }

        return sumOfBits * 2 >= numbers.size
    }

    private tailrec fun findOneByCriteria(
            numbers: List<BitSet>,
            iteration: Int = 0,
            predicate: (List<BitSet>, Int) -> Boolean
    ): BitSet {
        require(numbers.isNotEmpty())

        if (numbers.size == 1) return numbers.first()

        val filteredNumbers = numbers
                .filter { it[iteration] == predicate(numbers, iteration) }

        return findOneByCriteria(filteredNumbers, iteration + 1, predicate)
    }

    private class BitSet(nBits: Int) {
        val size: Int
            get() = bits.size

        private val bits = BooleanArray(nBits)

        companion object {
            fun fromString(s: String): BitSet {
                require(s.all { it == '1' || it == '0' })

                val result = BitSet(s.length)
                s.forEachIndexed { index, c ->
                    if (c == '1') result[index] = true
                }
                return result
            }
        }

        operator fun get(index: Int): Boolean {
            require(index in bits.indices)

            return bits[index]
        }

        operator fun set(index: Int, value: Boolean) {
            require(index in bits.indices)

            bits[index] = value
        }

        fun toInt(): Int {
            return this.toString().toInt(2)
        }

        fun flipBits(): BitSet {
            val result = BitSet(this.size)
            bits.forEachIndexed { index, bit ->
                if (!bit) result[index] = true
            }

            return result
        }

        override fun toString(): String {
            return bits
                    .map {
                        if (it) '1' else '0'
                    }
                    .joinToString("")
        }
    }
}