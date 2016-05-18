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
}