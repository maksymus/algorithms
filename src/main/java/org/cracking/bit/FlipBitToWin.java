package org.cracking.bit;

/**
 * You have an integer and you can flip exactly one bit from a O to a 1. Write code to
 * find the length of the longest sequence of 1 s you could create.
 *
 * EXAMPLE
 * Input: 1775 (or: 11011101111)
 * Output: 8
 */
public class FlipBitToWin {
    public static void main(String[] args) {
        System.out.println(flip((Integer.MAX_VALUE & ~(1 << 30))));
    }

    public static int flip(int input) {
        int leftCount = 0, rightCount = 0, maxCount = 0;

        for (int i = 0; i < 31; i++) {
            if ((input & 1 << i) == 0) {
                maxCount = Math.max(maxCount, rightCount + leftCount + 1);

                rightCount = leftCount;
                leftCount = 0;
            } else {
                leftCount++;
            }
        }

        return maxCount;
    }
}
