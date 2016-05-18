package org.interviewelements

import spock.lang.Specification

class HeapsTest extends Specification {
    def "mergeSortedIntegers test"() {
        expect:
        Heaps.mergeSortedIntegers(lists) == list

        where:
        lists                   || list
        []                      || []
        [[1]]                   || [1]
        [[1], [1], [3]]         || [1, 1, 3]
        [[1, 3, 7], [2, 4], []] || [1, 2, 3, 4, 7]
    }
}