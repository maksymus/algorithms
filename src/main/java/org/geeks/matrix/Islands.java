package org.geeks.matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Islands {

    public static void main(String[] args) {
        int count = new Islands().count(new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
        });

        System.out.println(count);

        char[][] regions = new Islands().regions(new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X'},
        });

        for (int i = 0; i < regions.length; i++) {
            for (int j = 0; j < regions[0].length; j++) {
                System.out.printf("%c ", regions[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded
     * by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges
     * of the grid are all surrounded by water.
     *
     * Example:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * Answer: 1
     */
    public int count(int[][] land) {
        int m = land.length;
        int n = land[0].length;

        int[] uf = new int[m*n];

        BiFunction<Integer, Integer, Integer> trans = (i, j) -> i * n + j;
        Function<Integer, Integer> root = i ->  {
            while (uf[i] != i) {
                uf[i] = uf[uf[i]];
                i = uf[i];
            }
            return i;
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                uf[trans.apply(i, j)] = trans.apply(i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0)
                    continue;

                int upper = i > 0 ? land[i-1][j] : 0;
                int left = j > 0 ? land[i][j-1] : 0;

                if (upper == 1)
                    uf[trans.apply(i, j)] = uf[trans.apply(i - 1, j)];

                if (left == 1)
                    uf[trans.apply(i, j)] = uf[trans.apply(i, j - 1)];
            }
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0)
                    continue;

                int r = root.apply(trans.apply(i, j));
                roots.add(r);
            }
        }

        return roots.size();
    }


    /**
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by
     * flipping all 'O's into 'X's in that surrounded region.
     *
     * For example,
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     *
     * Using BFS
     */
    public char[][] regions(char[][] land) {
        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            bfs(land, i, 0);
            bfs(land, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            bfs(land, 0, j);
            bfs(land, m - 1, j);
        }

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 'O')
                    land[i][j] = 'X';

                if (land[i][j] == 'Z')
                    land[i][j] = 'O';
            }
        }

        return land;
    }
    
    public void bfs(char[][] land, int i, int j) {
        int m = land.length;
        int n = land[0].length;

        Queue<Integer> queue = new LinkedList<>();
        if (land[i][j] == 'O')
            queue.add(i * n + j);

        while (!queue.isEmpty()) {
            int elem = queue.remove();
            int ii = elem / n;
            int jj = elem % n;

            land[ii][jj] = 'Z';

            if (ii - 1 >= 0 && land[ii-1][jj] == 'O')
                queue.add((ii - 1) * n + jj);

            if (ii + 1 < m && land[ii+1][jj] == 'O')
                queue.add((ii + 1) * n + jj);

            if (jj - 1 >= m && land[ii][jj-1] == 'O')
                queue.add(ii* n + jj - 1);

            if (jj + 1 < n && land[ii][jj+1] == 'O')
                queue.add(ii* n + jj + 1);
        }
    }
}
