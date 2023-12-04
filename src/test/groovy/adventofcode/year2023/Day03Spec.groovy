package adventofcode.year2023

import spock.lang.Specification

class Day03Spec extends Specification {

    def 'Should pass part 1'(String input, String output) {
        expect:
        new Day03(input).solvePart1() == output

        where:
        input            || output
        '''467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..''' || 4361
    }

    def 'Should pass part 2'(String input, String output) {
        expect:
        new Day03(input).solvePart2() == output

        where:
        input            || output
        '''467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..''' || 467835
    }
}
