package org.rust.arrays;

import java.util.function.*;

/**
 * Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
 *
 * Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
 * key = 3
 * Output : Found at index 8
 *
 * Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
 * key = 30
 * Output : Not found

 * Input : arr[] = {30, 40, 50, 10, 20}
 * key = 10
 * Output : Found at index 3
 */
public class SearchRotatedArray {
    public static void main(String[] args) {
        System.out.println(searchRotated(new int[] { 1, 3, 4, 6, 8, 9 }, 3));
        System.out.println(searchRotated(new int[] { 3, 4, 5, 6, 7, 1, 2 }, 6));
    }

    // search in sorted/rotated array  3 4 5 6 7 1 2
    private static int searchRotated(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[0] > arr[i]) {
                start = i;
                end = i - 1;
                break;
            }
        }

        final int shift = start;
        Function<Integer, Integer> shiftLeft =  (Integer i) -> i - shift >= 0 ? i - shift : arr.length + (i - shift);
        Function<Integer, Integer> shiftRight =  (Integer i) -> i + shift < arr.length ? i + shift :  (i + shift) - arr.length;

        int from = shiftLeft.apply(start);
        int to = shiftLeft.apply(end);

        while (from <= to) {
            int mid = from + (to - from) / 2;
            int elem = arr[shiftRight.apply(mid)];

            if (elem == x)
                return shiftRight.apply(mid);

            if (elem > x)
                to = mid - 1;

            if (elem < x)
                from = mid + 1;
        }

        return -1;
    }
}
