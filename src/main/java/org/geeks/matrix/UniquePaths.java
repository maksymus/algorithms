package org.geeks.matrix;

/**
 * A robot is located at the top-left corner of a m x n grid. It can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid. For example, there is one obstacle in the
 * middle of a 3x3 grid as illustrated below,
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 */
public class UniquePaths {

    public int recursive(int m, int n) {
        if (m == 1 || n == 1)
            return 1;

        return recursive(m - 1, n) + recursive(m, n - 1);
    }

    public int dynamic(int m, int n) {
        return obstacle(new int[m][n]);
    }

    public int obstacle(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++)
            dp[i][0] = 1;

        for (int j = 0; j < n; j++)
            dp[0][j] = 1;


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int upper = matrix[i-1][j] == 0 ? dp[i-1][j] : 0;
                int left = matrix[i][j-1] == 0 ? dp[i][j-1] : 0;

                dp[i][j] = upper + left;
            }
        }

        return dp[m-1][n-1];
    }
}
