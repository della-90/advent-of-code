package adventofcode.year2022

import spock.lang.Specification

class Day01Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day01(input).solvePart1() == result

        where:
        input     || result
        '''1000
2000
3000

4000

5000
6000

7000
8000
9000

10000''' || 24000
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day01(input).solvePart2() == result

        where:
        input     || result
        '''1000
2000
3000

4000

5000
6000

7000
8000
9000

10000''' || 45000
    }
}
