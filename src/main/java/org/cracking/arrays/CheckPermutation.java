package org.cracking.arrays;

import org.interviewelements.Array;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is a permutation of the other (nPn).
 */
public class CheckPermutation {
    public boolean isPermutation(String str1, String str2) {
        String s1 = str1 != null ? str1 : "";
        String s2 = str2 != null ? str2 : "";

        if (s1.length() != s2.length())
            return false;

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return false;
            }
        }

        return true;
    }
}
