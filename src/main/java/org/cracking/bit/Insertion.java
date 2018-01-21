package org.cracking.bit;

/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and
 * j. Write a method to insert M into N such that M starts at bit j and ends at bit i. You
 * can assume that the bits j through i have enough space to fit all of M. That is, if
 * M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
 * example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 *
 * EXAMPLE
 * Input:
 * N = 10000000000, M = 10011, i = 2, j = 6
 * Output: N = 10001001100
 */
public class Insertion {
    public static void main(String[] args) {
        int n = 0b10000000000;
        int m = 0b10011;

        int result = insert(n, m, 2, 6);
        System.out.println(Integer.toBinaryString(result));
    }

    // clear bits them merge bits
    public static int insert(int n, int m, int i, int j) {
        int ones = ~0;

        int left = ones << j + 1;
        int right = ~(~ones << i);

        int mask = left | right;

        return (n & mask) | (m << i);
    }
}
