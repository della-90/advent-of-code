package adventofcode.year2021

import spock.lang.Specification

class Day09Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day09(input).solvePart1() == result

        where:
        input                                                                                  || result
        '''2199943210
3987894921
9856789892
8767896789
9899965678''' || '15'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day09(input).solvePart2() == result

        where:
        input                   || result
        '''2199943210
3987894921
9856789892
8767896789
9899965678''' || '1134'
    }
}
