package org.rust.arrays;

import org.interviewelements.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.
 *
 * Examples:
 *
 * Input :
 * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
 * k = 3
 * Output :
 3 3 4 5 5 5 6
 *
 * Input :
 * arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
 * k = 4
 * Output :
 * 10 10 10 15 15 90 90
 */
public class SlidingWindowMax {
    public static void main(String[] args) {
        System.out.println(max(new int[] {1, 2, 3, 1, 4, 5, 2, 3, 6}, 3));
        System.out.println(max(new int[] {1, 2, 3, 4}, 10));
    }

    public static List<Integer> max(int[] arr, int k) {
        if (k < 1)
            throw new IllegalArgumentException("should be > 0");

        if (k == 1)
            return Arrays.stream(arr).boxed().collect(Collectors.toList());

        List<Integer> window = new ArrayList<>();
        int winSize = Math.min(k, arr.length);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < winSize; i++) {
            max = Math.max(max, arr[i]);
        }

        for (int i = 0; i < winSize; i++) {
            window.add(i, max);
        }

        for (int i = winSize; i < arr.length; i++) {
            int prev = window.get(i - 1);

            if (prev >= arr[i]) {
                window.add(i, prev);
            } else {
                for (int j = i - winSize + 1; j < i; j++) {
                    window.set(j, arr[i]);
                }

                window.add(i, arr[i]);
            }
        }


        return window.subList(0, Math.max(0, window.size() - winSize + 1));
    }
}
