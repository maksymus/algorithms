package org.geeks;

import java.util.Arrays;

/**
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between 
 * their sums is minimum.
 * 
 * If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and 
 * the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 * 
 * Example:
 *  Input:  arr[] = {1, 6, 11, 5} 
 *  Output: 1
 * Explanation:
 *  Subset1 = {1, 5, 6}, sum of Subset1 = 12 
 *  Subset2 = {11}, sum of Subset2 = 11     
 */
public class MinimumPartition {
    public int findMin(int[] arr) {
        int total = Arrays.stream(arr).sum();
        return findMin(arr, 0, 0, total);
    }
    
    private int findMin(int[] arr, int i, int count, int total) {
        if (i == arr.length)
            return Math.abs((total - count)  - count);
        
        return Math.min(findMin(arr, i + 1, count + arr[i], total), findMin(arr, i + 1, count, total));
    }
    
    public static void main(String[] args) {
        System.out.println(new MinimumPartition().findMin(new int[] { 1, 6, 11, 5 }));
    }
}
