package org.geeks.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * For example, given array A = [2,3,1,1,4], the minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGame {
    public List<Integer> jumps(int arr[]) {
        if (arr.length < 2)
            return Arrays.asList();

        int[] hops = new int[arr.length];
        int[] jumps = new int[arr.length];

        Arrays.fill(hops, 0, arr.length - 1, Integer.MAX_VALUE);

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr[i]; j >= 1; j--) {
                if (i + j >= arr.length) {
                    jumps[i] = arr.length - 1;
                    hops[i] = 1;
                    break;
                }

                jumps[i] = hops[i + j] < hops[i] ? i + j : jumps[i];
                hops[i] = Math.min(hops[i], hops[i + j] + 1);
            }
        }

        if (hops[0] == 0)
            return Arrays.asList();

        List<Integer> res = new ArrayList<>(Arrays.asList(arr[0]));

        for (int i = jumps[0]; i != 0; i = jumps[i])
            res.add(arr[i]);

        return res;
    }

    public static void main(String[] args) {
        List<Integer> jumps = new JumpGame().jumps(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9});
        String res = jumps.stream().map(i -> Integer.valueOf(i).toString()).collect(Collectors.joining("->"));
        System.out.println(res);
    }
}
