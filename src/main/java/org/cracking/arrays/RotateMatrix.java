package org.cracking.arrays;

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 */
public class RotateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3},
                          {4, 5, 6},
                          {7, 8, 9}};
        new RotateMatrix().rotate(matrix);

        for (int[] rows : matrix) {
            for (int columns : rows) {
                System.out.print(columns + "\t");
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;

        if (matrix.length != matrix[0].length)
            return;

        int length = matrix.length;

        for (int layer = 0; layer <= length / 2; layer++) {
            int last = length - layer - 1;

            for (int i = layer; i < last; i++) {
                int first = length - i - 1;

                int tmp = matrix[layer][i];
                matrix[layer][i] = matrix[first][layer];
                matrix[first][layer] = matrix[last][first];
                matrix[last][first] = matrix[i][last];
                matrix[i][last] = tmp;
            }
        }
    }
}
