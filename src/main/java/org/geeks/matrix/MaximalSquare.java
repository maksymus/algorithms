package org.geeks.matrix;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 * For example, given the following matrix:
 * 1101
 * 1101
 * 1111
 * Return 4.
 */
public class MaximalSquare {
    public static void main(String[] args) {

    }

    public int find(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < m; i++)
            dp[i][0] = matrix[i][0];

        for (int j = 0; j < n; j++)
            dp[0][j] = matrix[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        int max = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        return max;
    }
}
