package org.rust.math;

/**
 * Generate all unique partitions of an integer
 * Given a positive integer n, generate all possible unique ways to represent n as sum of positive integers.
 * Examples:
 *
 *   Input: n = 2
 *   Output:
 *   2
 *   1 1
 *
 *   Input: n = 3
 *   Output:
 *   3
 *   2 1
 *   1 1 1
 *   Note: 2+1 and 1+2 are considered as duplicates.
 *
 *   Input: n = 4
 *   Output:
 *   4
 *   3 1
 *   2 2
 *   2 1 1
 *   1 1 1 1
 */
public class AllSumCombinations {
    public static void main(String[] args) {
        print(4);
    }

    public static void print(int n) {
        int[] partitions = new int[n];
        int pos = 0;
        partitions[pos] = n;

        while (true) {
            print(partitions, pos);

            int accumulator = 0;
            while (pos >= 0 && partitions[pos] == 1) {
                accumulator += partitions[pos];
                pos--;
            }

            if (pos < 0)
                break;

            partitions[pos]--;
            accumulator++;

            while (accumulator > partitions[pos]) {
                partitions[pos+1] = partitions[pos];
                accumulator -= partitions[pos];
                pos++;
            }


            partitions[pos+1] = accumulator;
            pos++;
        }
    }

    private static void print(int[] partitions, int pos) {
        for (int i = 0; i <= pos; i++) {
            System.out.print(partitions[i] + ", ");
        }
        System.out.println();
    }

}
