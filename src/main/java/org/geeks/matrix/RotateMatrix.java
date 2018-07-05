package org.geeks.matrix;


/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Follow up:
 * Could you do this in-place?
 */

//  Transpose
//  1  5  9  13
//  2  6  10 14
//  3  7  11 15
//  4  8  12 16

// Result
//  13 9  5 1
//  14 10 6 2
//  15 11 7 3
//  16 12 8 4
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] rotated = new RotateMatrix().rotate(new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        });

        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated.length; j++) {
                System.out.printf("%d ", rotated[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * transpose and move colums
     */
    public int[][] rotate(int[][] matrix) {
        int n = matrix.length;

        //transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        //move columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = tmp;
            }
        }

        return matrix;
    }

    public int[][] rotate1(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }

        return matrix;
    }

}
