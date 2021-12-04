package adventofcode.year2021

import spock.lang.Specification

class Day03Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day03(input).solvePart1() == result

        where:
        input     || result
        '''00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010''' || '198'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day03(input).solvePart2() == result

        where:
        input     || result
        '''00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010''' || '230'
    }
}
