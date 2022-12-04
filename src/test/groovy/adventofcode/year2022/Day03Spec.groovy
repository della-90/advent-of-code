package adventofcode.year2022

import spock.lang.Specification

class Day03Spec extends Specification {

    def 'Should pass part 1'(String input, String result) {
        expect:
        new Day03(input).solvePart1() == result

        where:
        input     || result
        '''vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw''' || 157
    }

    def 'Should pass part 2'(String input, String result) {
        expect:
        new Day03(input).solvePart2() == result

        where:
        input     || result
        '''vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw''' || 70
    }
}
