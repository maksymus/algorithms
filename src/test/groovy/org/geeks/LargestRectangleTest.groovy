package org.geeks

import spock.lang.Specification

class LargestRectangleTest extends Specification {
    def "test optim"() {
        expect:
        LargestRectangle.optim(heights as int[]) == max

        where:
        heights               || max
        [6, 2, 5, 4, 5, 1, 6] || 12
        [2, 1, 5, 6, 2, 3]    || 10
        []                    || 0
        [1, 3, 6]             || 6
    }
}
