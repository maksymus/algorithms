package org.geeks

import org.geeks.dynamic.PatternMatching
import spock.lang.Specification

class PatternMatchingSpec extends Specification {
    def "test match"() {
        given:
        def patternMatching = new PatternMatching()
        expect:
        res == patternMatching.match(string, pattern)

        where:
        string              | pattern               || res
        'baaabab'           | '*****ba*****ab'      || true
        'baaabab'           | 'baaa?ab'             || true
        'baaabab'           | 'ba*a?'               || true
        'baaabab'           | 'a*ab'                || false
    }
}