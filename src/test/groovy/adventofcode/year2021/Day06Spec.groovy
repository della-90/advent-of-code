package adventofcode.year2021

import spock.lang.Specification

class Day06Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day06(input).solvePart1() == result

        where:
        input       || result
        '3,4,3,1,2' || '5934'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day06(input).solvePart2() == result

        where:
        input       || result
        '3,4,3,1,2' || '26984457539'
    }
}
