package org.geeks

import spock.lang.Specification

class CandyTest extends Specification {
    def "test candies"() {
        given:
        def candy = new Candy()
        expect:
        candy.candies(ratings as int[]) == candies as int[]

        where:
        ratings         || candies
        [1, 3, 2, 5, 4] || [1, 2, 1, 2, 1]
    }
}
