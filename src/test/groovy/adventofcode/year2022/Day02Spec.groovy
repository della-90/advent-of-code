package adventofcode.year2022

import spock.lang.Specification

class Day02Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day02(input).solvePart1() == result

        where:
        input     || result
        '''A Y
B X
C Z''' || 15
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day02(input).solvePart2() == result

        where:
        input     || result
        '''A Y
B X
C Z''' || 12
    }
}
