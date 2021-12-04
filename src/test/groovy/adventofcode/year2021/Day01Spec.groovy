package adventofcode.year2021

import spock.lang.Specification

class Day01Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day01(input).solvePart1() == result

        where:
        input     || result
        '''199
200
208
210
200
207
240
269
260
263''' || '7'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day01(input).solvePart2() == result

        where:
        input     || result
        '''199
200
208
210
200
207
240
269
260
263''' || '5'
    }
}
