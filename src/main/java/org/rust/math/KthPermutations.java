package org.rust.math;

import java.util.Arrays;

/**
 * Given a set of n elements find their kth permutation.
 * Given numbers n and k, 1 <= k < INT_MAX, return kth permutation of the set [1,2,…,n].
 *
 * For example, given n=3 and k=4, the permutations of [1,2,3] in order are:
 * “123”
 * “132”
 * “213”
 * “231”
 * “312”
 * “321”
 * k=4th permutation is “231”
 */
public class KthPermutations {
    public static void main(String[] args) {
        find(new int[] { 1, 2, 3 }, 4);
    }

    public static void find(int[] set, int k) {
        // todo add k range check
        int factorial = factorial(set.length);
        if (factorial < k)
            throw new IllegalArgumentException("k is too high");

        while (set.length > 0) {
            int index = index(set.length, k);

            System.out.print(set[index] + ", ");

            k = k - factorial(set.length) / set.length * index;
            set = remove(set, index);
        }
    }

    private static int index(int n, int k) {
        int factorial = factorial(n - 1);

        int part = factorial;
        int index = (k - 1) / part;

        return index;
    }

    private static int factorial(int n) {
        int factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    private static int[] remove(int[] arr, int pos) {
        int[] tmp = new int[arr.length - 1];

        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != pos) {
                tmp[j++] = arr[i];
            }
        }

        return tmp;
    }
}
