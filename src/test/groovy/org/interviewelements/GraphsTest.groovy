package org.interviewelements

import static java.awt.Color.BLACK
import static java.awt.Color.WHITE

import java.awt.Color
import java.awt.Point

import spock.lang.Specification

class GraphsTest extends Specification {
    def "MazeSolver 3x3"() {
        given:
        def colors = [
              [WHITE, WHITE, WHITE], 
              [WHITE, BLACK, WHITE],
              [WHITE, BLACK, WHITE]
        ];
    
        def mazeSolver = new MazeSolver(colors as Color[][])
        
        expect:
        mazeSolver.solve(start, end) == path

        where:
        start               | end               || path
        new Point(0, 0)     | new Point(0, 0)   || [new Point(0, 0)]
        new Point(0, 0)     | new Point(0, 1)   || [new Point(0, 0), new Point(0, 1)]
        new Point(0, 0)     | new Point(2, 2)   || [new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 2), new Point(2, 2)]
    }
}