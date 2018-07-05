package org.rust.arrays;

import java.util.Arrays;

/**
 * Given an array of integers, rotate the array by 'N' elements.
 * 1, 2, 3, 4, 5, 6, 7
 * => 5
 * 3, 4, 5, 6, 7, 1, 2
 *
 * https://www.geeksforgeeks.org/array-rotation/
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] res = rotate(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 6);
        System.out.println(Arrays.toString(res));
    }

    private static int[] rotate(int[] arr, int d) {
        int n = arr.length;

        int i, j, k, temp;
        for (i = 0; i < gcd(d, n); i++) {
            /* move i-th values of blocks */
            temp = arr[i];
            j = i;
            while (true) {
                k = j + d;
                if (k >= n)
                    k = k - n;
                if (k == i)
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }

        return arr;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
