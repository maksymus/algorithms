package org.cracking.dynamic;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the stairs.
 */
public class TripleStep {
    public static void main(String[] args) {
        System.out.println(countDynamic(4));
    }

    public static int count(int n) {
        if (n < 0)
            return 0;

        if (n == 0)
            return 1;

        return count(n - 1) + count(n - 2) + count(n - 3);
    }

    public static int countDynamic(int n) {
        if (n < 1)
            throw new IllegalArgumentException("should be > 0");

        int[] cache = new int[n < 3 ? 3 : n];

        cache[0] = 1;
        cache[1] = 2;
        cache[2] = 4;

        for (int i = 3; i < n; i++) {
            cache[i] = cache[i -1] + cache[i-2] + cache[i-3];
        }

        return cache[n-1];
    }

}
