package org.cracking.dynamic;

import org.interviewelements.Array;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits" such that
 * the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
 * the bottom right.
 */
public class GridRobot {

    private enum Direction { RIGHT, DOWN }

    private static class Cell {
        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        int row;
        int column;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (row != cell.row) return false;
            return column == cell.column;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            return result;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }

    private static class Link {
        public Link(Link previous, Cell cell) {
            this.previous = previous;
            this.cell = cell;
        }


        Link previous;
        Cell cell;
    }

    private int[][] grid; // 0 - normal cell, !0 - off limits
    private final int numRows;
    private final int numColumns;

    private Set<Cell> visited = new HashSet<>();

    public GridRobot(int[][] grid) {
        this.grid = grid;
        this.numRows = grid.length;
        this.numColumns = grid[0].length;
    }

    public List<Cell> walk() {
        Cell start = new Cell(0, 0);

        List<Cell> path = new ArrayList<>();
        path.add(start);

        return walk(start, path, Direction.RIGHT);
    }

    public List<Cell> walk(Cell cell, List<Cell> path, Direction direction) {
        if (isVisited(cell))
            return Arrays.asList();

        visit(cell);

        if (isEnd(cell))
            return path;

        Cell right = right(cell);
        Cell down = down(cell);

        if (direction == Direction.RIGHT) {
            List<Cell> downWalk = tryWalk(down, path, Direction.DOWN);
            if (!downWalk.isEmpty())
                return downWalk;

            List<Cell> rightWalk = tryWalk(right, path, Direction.RIGHT);
            if (!rightWalk.isEmpty())
                return rightWalk;
        } else {
            List<Cell> rightWalk = tryWalk(right, path, Direction.RIGHT);
            if (!rightWalk.isEmpty())
                return rightWalk;

            List<Cell> downWalk = tryWalk(down, path, Direction.DOWN);
            if (!downWalk.isEmpty())
                return downWalk;
        }

        return Arrays.asList();
    }

    private List<Cell> tryWalk(Cell cell, List<Cell> path, Direction direction) {
        if (cell == null)
            return Arrays.asList();

        List<Cell> nextPath = new ArrayList<>(path);
        nextPath.add(cell);
        List<Cell> walk = walk(cell, nextPath, direction);
        if (!walk.isEmpty())
            return walk;

        return Arrays.asList();
    }

    private boolean isOffLimits(int r, int c) {
        return grid[r][c] != 0;
    }

    private boolean isValid(Cell cell) {
        if (cell.row >= numRows || cell.column >= numColumns)
            return false;

        if (isOffLimits(cell.row, cell.column))
            return false;

        return true;
    }

    private boolean isVisited(Cell cell) {
        return visited.contains(cell);
    }

    private boolean visit(Cell cell) {
        return visited.add(cell);
    }

    private boolean isEnd(Cell cell) {
        return cell.row == numRows - 1 && cell.column == numColumns - 1;
    }

    private Cell right(Cell cell) {
        int nextColumn = cell.column + 1;

        Cell right = new Cell(cell.row, nextColumn);

        if (!isValid(right))
            return null;

        return right;
    }

    private Cell down(Cell cell) {
        int nextRow = cell.row + 1;

        Cell down = new Cell(nextRow, cell.column);

        if (!isValid(down))
            return null;

        return down;
    }

    public static void main(String[] args) {
        GridRobot gridRobot = new GridRobot(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0},
        });

        List<Cell> walk = gridRobot.walk();
        walk.forEach(cell -> System.out.println(cell));
    }
}
