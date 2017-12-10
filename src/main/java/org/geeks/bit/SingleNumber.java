package org.geeks.bit;

public class SingleNumber {

    /**
     * Given an array where every element occurs two times, except one element which occurs only once. Find the
     * element that occurs once. Expected time complexity is O(n) and O(1) extra space.
     *
     * Examples:
     * Input: arr[] = {12, 12, 1, 1, 2, 3, 3}
     * Output: 2
     */
    public int twoTimes(int[] arr) {
        int x = 0;
        for (int a : arr) {
            x = x ^ a;
        }
        return x;
    }

    /**
     * Given an array where every element occurs three times, except one element which occurs only once. Find the
     * element that occurs once. Expected time complexity is O(n) and O(1) extra space.
     *
     * Examples:
     * Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
     * Output: 2
     */
    public int threeTimes(int[] a) {
        int ones = 0, twos = 0;
        for (int i = 0; i < a.length; i++) {
            twos |= ones & a[i];
            ones ^= a[i];
            int threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber().twoTimes(new int[]{ 12, 1, 13, 12, 1 }));
        System.out.println(new SingleNumber().threeTimes(new int[]{ 12, 1, 13, 12, 1, 12, 1 }));
    }
}
