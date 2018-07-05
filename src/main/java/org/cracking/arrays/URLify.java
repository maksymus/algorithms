package org.cracking.arrays;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string. (Note: If implementing in Java, please use a character array so that you can
 * perform this operation in place.)
 * EXAMPLE
 *
 * Input: "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 */
public class URLify {
    public static void main(String[] args) {
        String res = URLify.urlify("Mr John Smith    ", 13);
        System.out.println(res);
    }

    public static String urlify(String str, int length) {
        char[] chars = str.toCharArray();

        int pointer = str.length() - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                chars[pointer] = '0';
                chars[pointer - 1] = '2';
                chars[pointer - 2] = '%';
                pointer = pointer - 3;

            } else {
                chars[pointer] = chars[i];
                pointer = pointer - 1;
            }
        }

        return String.valueOf(chars);
    }
}
