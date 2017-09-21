package org.geeks.dynamic;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0
 * or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal
 * path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * -2 (K) -3	3
 * -5	  -10	1
 * 10	  30   -5 (P)
 */
public class DungeonGame {
    public int[][] walk(int[][] cells) {
        int nrows = cells.length;
        int ncols = cells[0].length;

        int[][] dp = new int[nrows][ncols];

        dp[nrows-1][ncols-1] = Math.max(cells[nrows-1][ncols-1] * -1, 0) + 1;

        for (int i = nrows - 2; i >= 0 ; i--)
            dp[i][ncols-1] = Math.max(dp[i+1][ncols-1] - cells[i][ncols-1], 1);

        for (int j = ncols - 2; j >= 0 ; j--)
            dp[nrows-1][j] = Math.max(dp[nrows-1][j+1] - cells[nrows-1][j], 1);

        for (int i = nrows - 2; i >= 0 ; i--) {
            for (int j = ncols - 2; j >= 0 ; j--) {
                int right = Math.max(dp[i][j+1] - cells[i][j], 1);
                int down = Math.max(dp[i+1][j] - cells[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        int[][] walk = new DungeonGame().walk(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        });

        for (int i = 0; i < walk.length; i++) {
            for (int j = 0; j < walk[0].length; j++) {
                System.out.print(walk[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Min health: " + walk[0][0]);
    }

}
