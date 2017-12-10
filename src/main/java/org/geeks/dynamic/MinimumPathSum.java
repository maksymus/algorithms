package org.geeks.dynamic;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the
 * sum of all numbers along its path.
 */
public class MinimumPathSum {
    public int minSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] table = new int[m][n];

        table[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            table[i][0] = table[i-1][0] + grid[i][0];

        for (int j = 1; j < n; j++)
            table[0][j] = table[0][j - 1] + grid[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int down = table[i-1][j] + grid[i][j];
                int right = table[i][j-1] + grid[i][j];
                grid[i][j] = Math.min(down, right);
            }
        }

        return table[m-1][n-1];
    }
}
