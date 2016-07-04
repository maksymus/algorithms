package org.interviewelements

import spock.lang.Specification

class DynamicProgrammingTest extends Specification {
    def "levenshtein test"() {
        expect:
        DynamicProgramming.levenshtein(a, b) == res

        where:
        a           | b                 || res
        ""          | ""                || 0
        "hello"     | "world"           || 4
    }
}