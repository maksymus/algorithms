package org.cracking.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */
public class IsUnique {
    public static void main(String[] args) {
        System.out.println(IsUnique.isUnique("hello"));
        System.out.println(IsUnique.isUnique("cash"));
    }

    public static boolean isUnique(String str) {
        if (str.length() < 2)
            return false;

        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                return false;
            }
        }

        return true;
    }
}
