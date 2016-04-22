package org.interviewelements

import spock.lang.Specification

class ArrayTest extends Specification {
    def "permutation test"() {
        expect:
        Array.permutation(list) == permutations

        where:
        list        || permutations
        []          || []
        [1]         || [[1]]
        [1, 2]      || [[1, 2], [2, 1]]
        [1, 2, 3]   || [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }
}