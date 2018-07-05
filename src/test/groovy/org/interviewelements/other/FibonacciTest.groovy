package org.interviewelements.other

import spock.lang.Specification

class FibonacciTest extends Specification {
    def "fibonacci test"() {
        expect:
            Fibonacci.fib(n) == res

        where:
            n   || res
            0   || 0
            1   || 1
            2   || 1
            3   || 2
            10  || 55
            100 || 354224848179261915075
            172 || 394810887814999156320699623170776339
            300 || 222232244629420445529739893461909967206666939096499764990979600
    }
}