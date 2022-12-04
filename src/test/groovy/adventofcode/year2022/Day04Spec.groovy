package adventofcode.year2022

import spock.lang.Specification

class Day04Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day04(input).solvePart1() == result

        where:
        input     || result
        '''2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8''' || 2
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day04(input).solvePart2() == result

        where:
        input     || result
        '''2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8''' || 4
    }
}
