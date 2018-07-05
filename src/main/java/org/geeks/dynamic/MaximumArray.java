package org.geeks.dynamic;

public class MaximumArray {

    /**
     * Find the contiguous subarray within an arr (containing at least one number) which has the largest sum.
     * For example, given the arr [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     */
    public int maxSubarray(int[] arr) {
        int max = arr[0];
        int sum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(sum + arr[i], arr[i]);
            max = Math.max(max, sum);
        }

        return max;
    }
}
