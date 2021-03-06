package adventofcode

import adventofcode.year2021.Day09
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    val day = getClass<Day09>()

    println(measureTimedValue(day::solvePart1))
    println(measureTimedValue(day::solvePart2))
}

private inline fun <reified T : AbstractPuzzle> getClass(): T {
    val className = T::class.simpleName?.lowercase() ?: throw IllegalArgumentException("Can't detect class name")
    val input = T::class.java.getResource("$className.txt")?.readText()
    return T::class.java.getConstructor(String::class.java).newInstance(input)
}