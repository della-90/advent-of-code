package adventofcode.year2023

import spock.lang.Specification

class Day01Spec extends Specification {

    def 'Should pass part 1'(String input, String output) {
        expect:
        new Day01(input).solvePart1() == output

        where:
        input            || output
        '''1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet''' || 142
    }

    def 'Should pass part 2'(String input, String output) {
        expect:
        new Day01(input).solvePart2() == output

        where:
        input || output
        '''two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen''' || 281
        '''eighthree
sevenine''' || 162
        'oneight' || 18
    }
}
