package org.rust.arrays;

import org.interviewelements.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
 * 10 10 10 9 15 15 90 90
 */
public class SlidingWindowMax {
    public static void main(String[] args) {
        System.out.println(max(new int[] {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, 4));
//        System.out.println(max(new int[] {1, 2, 3, 4}, 10));
    }

    public static List<Integer> max(int[] arr, int k) {
        if (k < 1 || k >= arr.length)
            throw new IllegalArgumentException("should be > 0 and < array length");

        if (k == 1)
            return Arrays.stream(arr).boxed().collect(Collectors.toList());

        List<Integer> window = new ArrayList<>();

        int[] slide = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i >= k - 1)
                window.add(i - k + 1, Math.max(slide[i - k + 1], arr[i]));

            int slideStart = Math.max(i - k + 2, 0);

            if (arr[i] > slide[slideStart]) {
                for (int j = slideStart; j < i; j++) {
                    slide[j] = arr[i];
                }
            }

            slide[i] = arr[i];
        }

        return window;
    }
}
