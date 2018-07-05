package org.cracking.bit;

/**
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
 * print the binary representation. If the number cannot be represented accurately in binary with at
 * most 32 characters, print"ERROR:'
 */
public class BinaryToString {
    public static void main(String[] args) {
        System.out.println(toBin(0.125));

    }

    public static String toBin(double num) {
        if (num <= 0 || num >= 1)
            throw new IllegalArgumentException("number should be between 0 and 1 ");

        StringBuilder sb = new StringBuilder();
        sb.append(".");

        while (num != 1) {
            if (sb.length() >= 32)
                throw new IllegalArgumentException("cannot be represented accurately in binary with at most 32 characters");

            num = num * 2.;

            if (num >= 1) {
                sb.append("1");
                num -= 1;
            } else {
                sb.append("0");
            }
        }

        return sb.toString();
    }
}
