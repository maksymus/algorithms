package org.rust.math;

/**
 * Find all subsets of a given set.
 *
 * Input:
 * S = {a, b, c, d}
 * Output:
 * {}, {a} , {b}, {c}, {d}, {a,b}, {a,c},
 * {a,d}, {b,c}, {b,d}, {c,d}, {a,b,c},
 * {a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}
 */
public class AllSubsets {
    public static void main(String[] args) {
        print("1234".toCharArray());
    }

    /**
     * For 3 elements 2^3 subset exist (1 means element present in subset)
     * 0 0 0
     * 0 0 1
     * 0 1 0
     * 0 1 1
     * 1 0 0
     * 1 0 1
     * 1 1 0
     * 1 1 1
     */
    public static void print(char[] chars) {
        int n = chars.length;

        // walk trough all 2^n subsets
        for (int i = 0; i < Math.pow(2, n); i++) {
            System.out.print("{");

            // walk trough each element
            for (int j = 0; j < n; j++) {

                // print element if present
                if ((i & (1 << j)) > 0) {
                    System.out.print(chars[j]);
                }
            }

            System.out.println("}");
        }
    }
}
