package org.geeks

import spock.lang.Specification

class SimplifyPathTest extends Specification {
    def "test simplify"() {
        expect:
        SimplifyPath.simplify(path) == simple

        where:
        path              || simple
        "/home/"          || "/home"
        "/a/./b/../../c/" || "/c"
        "/../"            || "/"
        "/home//foo/"     || "/home/foo"
    }
}
