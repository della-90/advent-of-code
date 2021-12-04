package adventofcode.year2021

import spock.lang.Specification

class Day02Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day02(input).solvePart1() == result

        where:
        input     || result
        '''forward 5
down 5
forward 8
up 3
down 8
forward 2''' || '150'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day02(input).solvePart2() == result

        where:
        input     || result
        '''forward 5
down 5
forward 8
up 3
down 8
forward 2''' || '900'
    }
}
