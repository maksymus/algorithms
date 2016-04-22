package org.interviewelements
import spock.lang.Specification

class StacksQueuesTest extends Specification {
    def pushStackMax(List<Integer> values) {
        def stackMax = new StacksQueues.StackMax<Integer>()
        values.each { v -> stackMax.push(v) }

        stackMax.max()

        def max = new ArrayList<Integer>()
        while (!stackMax.isEmpty()) {
            max.add(stackMax.max())
            stackMax.pop()
        }

        return max
    }

    def "test StackMax"() {
        when:
        pushStackMax([])
        then:
        thrown(EmptyStackException)

        expect:
        pushStackMax(list) == max
        where:
        list                || max
        [1]                 || [1]
        [1, 2]              || [2, 1]
        [1, 5, 3, 4, 6]     || [6, 5, 5, 5, 1]
    }
}