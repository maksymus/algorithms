package org.geeks.bit;

/**
 * Write an efficient program to count number of 1s in binary representation of an integer.
 *
 * Examples
 *
 * Input : n = 6
 * Output : 2
 * Binary representation of 6 is 110 and has 2 set bits
 *
 * Input : n = 13
 * Output : 3
 * Binary representation of 11 is 1101 and has 3 set bits
 */
public class CountSetBits {

    /**
     * Simple Method Loop through all bits in an integer, check if a bit is set and if it is then increment the set bit
     * count.
     */
    public int count1(int i) {
        int count = 0;

        while (i > 0) {
            count += i & 1;
            i >>= 1;
        }

        return count;
    }

    /**
     * Brian Kernighan’s Algorithm:
     * Subtraction of 1 from a number toggles all the bits (from right to left) till the rightmost set bit(including the
     * righmost set bit). So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the righmost
     * set bit. If we do n & (n-1) in a loop and count the no of times loop executes we get the set bit count.
     *
     * Beauty of the this solution is number of times it loops is equal to the number of set bits in a given integer.
     *
     * 1  Initialize count: = 0
     * 2  If integer n is not zero
     *  (a) Do bitwise & with (n-1) and assign the value back to n
     *      n: = n & (n-1)
     *  (b) Increment count by 1
     *  (c) go to step 2
     * 3  Else return count
     */
    public int count2(int i) {
        int count = 0;

        while (i > 0) {
            i = i & i -1;
            count++;
        }

        return count;
    }

    /**
     * Using Lookup table: We can count bits in O(1) time using lookup table.
     * 0000: 0  0100: 4  1000: 8   1100: 12
     * 0001: 1  0101: 5  1001: 9   1101: 13
     * 0010: 2  0110: 6  1010: 10  1110: 14
     * 0011: 3  0111: 7  1011: 11  1111: 15
     */
    public int count3(int i) {
        int[] table = new int[] { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };

        int count = 0;

        while (i > 0) {
            count += table[i & 0xF];
            i >>= 4;
        }

        return count;
    }

    public static void main(String[] args) {
        CountSetBits countSetBits = new CountSetBits();

        System.out.println(countSetBits.count1(13));
        System.out.println(countSetBits.count2(13));
        System.out.println(countSetBits.count3(13));
    }
}
