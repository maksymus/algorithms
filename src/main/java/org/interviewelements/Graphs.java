package org.interviewelements;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Graphs {
    
    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver(new Color[][] {
            {WHITE, WHITE, WHITE}, 
            {WHITE, BLACK, WHITE},
            {WHITE, BLACK, WHITE}}
        );
        
        List<Point> path = mazeSolver.solve(new Point(0, 0), new Point(0, 0));
        System.out.println(path);
        
        boolean hasCycle = UnionFindCycle.hasCycle(new Pair[] {
            new Pair(0, 1), new Pair(1, 2), new Pair(2, 3)    
        }, 4);
        System.out.println("Has cycle: " + hasCycle);
    }
}

/**
 * Problem 16.1, pg. 132 : Given a 2D array of black and white entries representing a maze
 * with designated entrance and exit points, find a path from the entrance to the exit, if one exists.
 * Done with DFS
 */
class MazeSolver {
    private enum Direction { UP, DOWN, LEFT, RIGHT }
    
    private Color[][] maze;
    
    public MazeSolver(Color[][] maze) {
        this.maze = new Color[maze.length][maze[0].length];
        
        IntStream.range(0, maze.length)
            .forEach(i -> IntStream.range(0, maze[i].length)
                .forEach(j -> this.maze[i][j] = maze[i][j]));
    }
    
    /**
     * Depth first search.
     * @param start start point 
     * @param end end point
     */
    public List<Point> solve(Point start, Point end) {
        if (!isFeasible(start) || !isFeasible(end))
          throw new RuntimeException("wrong start or end point");
        
        List<Point> path = new ArrayList<>();
        if (solve(start, end, path))
            return path;
        
        return Arrays.asList();
    }
    
    private boolean solve(Point point, Point end, List<Point> path) {
        maze[point.x][point.y] = BLACK;
        path.add(point);
        
        if (point.equals(end))
            return true;
        
        for (Direction direction : Direction.values()) {
            Point next = move(point, direction);
            if (isFeasible(next)) {
                if (solve(next, end, path))
                    return true;
                
                path.remove(path.size() - 1);
            }
        }
        
        return false;
    }
    
    private Point move(Point from, Direction direction) {
        switch (direction) {
        case UP: return new Point(from.x - 1, from.y);
        case DOWN: return new Point(from.x + 1, from.y);
        case LEFT: return new Point(from.x, from.y - 1);
        case RIGHT: return new Point(from.x, from.y + 1);
        default: return new Point(from.x - 1, from.y);
        }
    } 
    
    private boolean isFeasible(Point point) {
        return point.x >= 0 && point.x < maze.length 
                && point.y >= 0 && point.y < maze[0].length
                && maze[point.x][point.y] == WHITE;
    }
}

/**
 * Detect Cycle in a an Undirected Graph using Union-Find
 */
class Pair {
    int from; int to;
    public Pair(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

class UnionFindCycle {
    public static boolean hasCycle(Pair[] pairs, int edges) {
        int[] uf = new int[edges];
        for (int i = 0; i < uf.length; i++)
            uf[i] = i;

        for (Pair pair : pairs) {
            int x = find(uf, pair.from);
            int y = find(uf, pair.to);
            
            if (x == y)
                return true;
            
            union(uf, pair.from, pair.to);
        }

        return false;
    }

    private static void union(int[] uf, int from, int to) {
        uf[to] = from;
    }

    private static int find(int[] uf, int from) {
        int cur = from;
        for (; uf[cur] != cur; cur = uf[cur]);
        return cur;
    }
}