package org.geeks.dynamic;

import java.util.Arrays;

/**
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given 
 * sequence such that all elements of the subsequence are sorted in increasing order. For example, the length of LIS for 
 * {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 *
 */
public class LongestIncreasingSubsequence {
    public int lis(int[] arr) {
        int lengths[] = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            lengths[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lengths[i] < lengths[j] + 1) {
                    lengths[i] = lengths[j] + 1;
                }
            }
        }
        
        return Arrays.stream(lengths).max().getAsInt();
    }
    
    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().lis(new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 }));
    }
}
