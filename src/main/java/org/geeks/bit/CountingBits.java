package org.geeks.bit;

import java.util.Arrays;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * Example:
 *
 * For num = 5 you should return [0,1,1,2,1,2].
 */
public class CountingBits {
    public int[] count(int num) {
        int[] result = new int[num+1];

        int power2 = 1;

        for (int i = 1; i <= num ; i++) {
            if (i == power2) {
                result[i] = 1;
                power2 <<= 1;
            } else {
                result[i] = 1 + result[i - (power2 >> 1)];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountingBits().count(5)));
        System.out.println(Arrays.toString(new CountingBits().count(26)));
    }
}
