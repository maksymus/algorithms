package org.geeks.matrix;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example, given the following matrix:
 *
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        new SpiralMatrix().walk(new int[][]{
                {0,  1,  2},
                {3,  4,  5},
                {6,  7,  8},
                {9,  10, 11},
                {12, 13, 14},
        });
    }
    
    public int[] walk(int[][] matrix) {
        int rowlength = matrix.length;
        int collength = matrix[0].length;

        int[] ints = new int[rowlength * collength];

        int m = rowlength;
        int n = collength;

        for (int i = 0, count = 0, pos = 0, step = 1; i < rowlength * collength; i++) {
            int elem = matrix[pos / collength][pos % collength];

            ints[i] = elem;
            System.out.printf("%d ", elem);

            count++;

            if (count == n && Math.abs(step) == 1) {
                m--;
                count = 0;
                step = collength * step;
            } else if (count == m && Math.abs(step) == collength) {
                n--;
                count = 0;
                step = step > 0 ? -1 : 1;
            }

            pos = pos + step;
        }
        
        return ints;
    }
}
