package adventofcode.year2021

import spock.lang.Specification

class Day07Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day07(input).solvePart1() == result

        where:
        input       || result
        '16,1,2,0,4,2,7,1,2,14' || '37'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day07(input).solvePart2() == result

        where:
        input       || result
        '16,1,2,0,4,2,7,1,2,14' || '168'
    }
}
