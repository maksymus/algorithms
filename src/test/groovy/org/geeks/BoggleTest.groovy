package org.geeks
import spock.lang.Specification


class BoggleTest extends Specification {

    def "test findWords"() {
        given:
            def dictionary = [ "geek", "for", "quiz", "go" ] as Set
            def char[][] maze = [
                    [ 'g', 'i', 'z' ],
                    [ 'u', 'e', 'k' ],
                    [ 'q', 's', 'e' ] ]
        
            def boggle = new Boggle(maze, dictionary)
        when:
            def words = boggle.findWords()
        then:
            words == ['quiz', 'geek'] as Set
    }
}
