package org.interviewelements

import spock.lang.Specification


class SortingTest extends Specification {
    def "intersectArrays test"() {
        expect:
        Sorting.intersectArrays(a, b) == c

        where:
        a                   | b                 || c
        [1, 2, 3]           |[4, 5, 6]          || [1, 2, 3, 4, 5, 6]
        [1, 3, 6]           |[2, 4, 5]          || [1, 2, 3, 4, 5, 6]
    }
}