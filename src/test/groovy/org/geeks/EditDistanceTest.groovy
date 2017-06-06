package org.geeks

import org.geeks.dynamic.EditDistance
import spock.lang.Specification

class EditDistanceTest extends Specification {
    def "test calculate"() {
        given:
            def editDistance = new EditDistance()
        expect:
            distance == editDistance.calculate(string1, string2)

        where:
            string1             | string2               || distance
            'sunday'            | 'saturday'            || 3
            ''                  | ''                    || 0
            'text'              | 'test'                || 1 
    }       
}
