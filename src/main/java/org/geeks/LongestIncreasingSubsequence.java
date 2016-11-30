package org.geeks;

import java.util.Arrays;

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
