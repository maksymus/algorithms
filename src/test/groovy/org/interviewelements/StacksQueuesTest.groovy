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

    def "test TwoStackQueue NoSuchElementException"() {
        when:
        new StacksQueues.TwoStackQueue().dequeue()
        then:
        thrown(NoSuchElementException)
    }

    def "test TwoStackQueue"() {
        setup:
        def queue = new StacksQueues.TwoStackQueue<Integer>()
        when:
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)

        def q1 = queue.dequeue()
        def q2 = queue.dequeue()

        queue.enqueue(4)
        queue.enqueue(5)
        queue.enqueue(6)

        def q3 = queue.dequeue()
        def q4 = queue.dequeue()
        def q5 = queue.dequeue()
        def q6 = queue.dequeue()

        then:
        q1 == 1
        q2 == 2
        q3 == 3
        q4 == 4
        q5 == 5
        q6 == 6
    }
}