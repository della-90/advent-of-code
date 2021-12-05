package adventofcode.year2021

import spock.lang.Specification

class Day05Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day05(input).solvePart1() == result

        where:
        input     || result
        '''0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2''' || '5'
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day05(input).solvePart2() == result

        where:
        input     || result
        '''0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2''' || '12'
    }
}
