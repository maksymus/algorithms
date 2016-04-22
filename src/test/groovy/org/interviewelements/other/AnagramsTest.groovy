package org.interviewelements.other

import spock.lang.Specification

class AnagramsTest extends Specification {
    def "anagrams test"() {
        given:
            def anagrams = new Anagrams()
        expect:
            anagrams.findAnagrams(word) == result

        where:
            word        || result
            "ripples"   || ["slipper"] as Set
            "east"      || ["eats", "sate", "seat", "teas", "etas"] as Set
    }
}