package org.interviewelements
import org.util.Link
import spock.lang.Shared
import spock.lang.Specification

class LinkedListsTest extends Specification {
    @Shared def link1 = new Link(1)
    @Shared def link2 = new Link(2)
    @Shared def link3 = new Link(3)
    @Shared def link4 = new Link(4)
    @Shared def link5 = new Link(5)
    @Shared def link6 = new Link(6)
    @Shared def link7 = new Link(7)
    @Shared def link8 = new Link(8)

    def "reverse linked list test"() {
        given:


        expect:
        LinkedLists.reverse(l).toList() == reversed

        where:
        l                                                           || reversed
        link(new Link(10))                                          || [10]
        link(new Link(3), new Link(5))                              || [5, 3]
        link(new Link(3), new Link(5), new Link(8))                 || [8, 5, 3]
    }

    def "cycle linked list test"() {
        expect:
        LinkedLists.isCyclic(list) == result

        where:
        list                                                        || result
        link(link1)                                                 || false
        link(link2, link2)                                          || true
        link(link3, link4, link5)                                   || false
        link(link6, link7, link8, link6)                            || true
    }

    def link(Link ... links) {
        for (int i = 0; i < links.length - 1; i++) {
            links[i].next = links[i + 1]
        }

        links[0]
    }

    def plus(Link<Integer> prev, Link<Integer> next) {
        prev.next = next
        prev
    }
}