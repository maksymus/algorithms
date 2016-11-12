package org.sedgewick
import org.sedgewick.EvaluatePostfix;
import org.sedgewick.InfixToPostfixSimple;

import spock.lang.Specification


class StacksTest extends Specification {

    def "test InfixToPostfix"() {
        expect:
        new InfixToPostfixSimple().infixToPostfix(infix) == postfix

        where:
        infix               || postfix
        "1+3"               || "1 3 + "
        "1+3*2"             || "1 3 2 * + "
        "1*2+3"             || "1 2 * 3 + "
        "1+2*3*5"           || "1 2 3 5 * * + "
        "1*2+3*5"           || "1 2 * 3 5 * + "
        "3+4*5/6"           || "3 4 5 * 6 / + "
    }
    
    def "test EvaluatePostfix"() {
        expect:
        new EvaluatePostfix().evaluate(postfix) == result

        where:
        postfix                 || result
        "1"                     || 1
        "1 2 +"                 || 3
        "1 3 - 4 *"             || -8
    }
}
