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

    def "sumToZero test"() {
        expect:
        Array.sumsToZero(list as int[], 3) == res

        where:
        list                        || res
        []                          || false
        [1, 2, -3]                  || true
        [10, 1, 4, -2, -1, 13, 1]   || true
        [10, 1, 4, -2, -1, 13]      || false
    }
    
    def "rotateKSteps test"() {
        expect:
        Array.rotateKSteps(list as int[], k) == res

        where:
        list                        | k  || res
        []                          | 1  || []
        [1, 2, -3]                  | 0  || [1, 2, -3]
        [1, 2, -3]                  | -1 || [1, 2, -3]
        [1, 2, 3, 4, 5, 6, 7]       | 3  || [5, 6, 7, 1, 2, 3, 4] 
    }
}