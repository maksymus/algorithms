package org.interviewelements

import spock.lang.Specification

class BSearchTest extends Specification {
    def "bsearch test"() {
        expect:
        BSearch.bsearch(arr as int[], item) == pos

        where:
        arr                             | item  | pos
        []                              | 1     | -1
        [1]                             | 1     | 0
        [1, 3, 4, 6]                    | 4     | 2
        [1, 2]                          | 2     | 1
    }

    def "bsearchFirstLargest test"() {
        expect:
        BSearch.bsearchFirstLargest(arr as int[], item) == pos

        where:
        arr                             | item  | pos
        []                              | 1     | -1
        [1]                             | 1     | -1
        [1, 3, 4, 6]                    | 4     | 3
        [3, 4, 6]                       | 1     | 0
        [1, 2]                          | 2     | -1
        [1, 2]                          | 10    | -1
    }

    def "completionSearch test"() {
        expect:
        BSearch.completionSearch(salaries, budget) == res

        where:
        salaries                            | budget  | res
        [90d, 30d, 100d, 40d, 20d]          | 210     | 60
        [90d, 30d, 100d, 40d, 20d]          | 280     | 100
        [90d, 30d, 100d, 40d, 20d]          | 50      | 10
        [90d, 30d, 100d, 40d, 20d]          | 281     | 0
    }

    def "sqrt test"() {
        expect:
        def sqrt = BSearch.sqrt(x)
        Math.abs(x - sqrt * sqrt) < 0.0000001 == true

        where:
        x           | res
        2d          | 0
        0d          | 0
        1231231d    | 0
    }
}