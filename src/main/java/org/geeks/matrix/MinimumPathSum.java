package org.geeks.matrix;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int min = new MinimumPathSum().dp(new int[][]{
                {1,  1,  10, 10},
                {1,  10, 10, 10},
                {10, 1,  10, 10},
                {10, 10, 1,  10},
                {10, 10, 10, 1},
        });

        System.out.println(min);
    }

    public int dp(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = _dp(matrix);

        int result = Integer.MAX_VALUE;
        for (int j = 0; j < cols; j++) {
            int bottom = dp[rows][j + 1];
            result = Math.min(result, bottom);
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.printf("%2d ", dp[i][j]);
            }
            System.out.println();
        }

        return result;
    }

    public int[][] _dp(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows+1][cols+1];

        for (int i = 1; i <= rows; i++)
            dp[i][0] = Integer.MAX_VALUE;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int elem = matrix[i][j];

                int up = dp[i][j+1];
                int left = dp[i+1][j];
                int diag = dp[i][j];

                int min = Math.min(diag, Math.min(up, left));

                dp[i+1][j+1] = elem + min;
            }
        }

        return dp;
    }

    public int dfs(int[][] matrix) {
        int cols = matrix[0].length;

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < cols; i++) {
            int dfs = dfs(matrix, 0, i);

            if (dfs < result)
                result = dfs;
        }

        return result;
    }

    public int dfs(int[][] matrix, int i, int j) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int elem = matrix[i][j];

        if (i == rows - 1)
            return elem;

        int below = elem + dfs(matrix, i + 1, j);

        if (j == cols - 1)
            return  below;

        int right = elem + dfs(matrix, i, j + 1);
        int diag = elem + dfs(matrix, i + 1, j + 1);

        return Math.min(below, Math.min(right, diag));
    }
}
