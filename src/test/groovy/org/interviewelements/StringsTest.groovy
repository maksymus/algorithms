package org.interviewelements

import spock.lang.Specification

class StringsTest extends Specification {
    def "test runLengthEncoding"() {
        expect:
        Strings.runLengthEncoding(str) == result

        where:
        str             || result
        ""              || ""
        "a"             || "1a"
        "aaaabcccaa"    || "4a1b3c2a"
        "eeeffffe"      || "3e4f1e"
    }

    def "test robinKarpSubstring"() {
        expect:
        Strings.robinKarpSubstring(text, search) == index

        where:
        text                        | search        || index
        ""                          | ""            || 0
        "test"                      | "test"        || 0
        "a"                         | "ab"          || -1
        ""                          | "test"        || -1
        "test"                      | "xyz"         || -1
        "test"                      | "tess"        || -1
        "hello world"               | "world"       || 6
        "string"                    | "tri"         || 1
    }


    def "invert string test"() {
        expect:
        Strings.reverse(string) == reverse

        where:
        string              || reverse
        "hello"             || "olleh"
        "this is a test"    || "tset a si siht"
        ""                  || ""
        "a"                 || "a"
        "ab"                || "ba"
    }

}