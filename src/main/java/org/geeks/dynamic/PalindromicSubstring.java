package org.geeks.dynamic;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstring {

    /**
     * Finding the longest palindromic substring.
     * Given a string, find the longest substring which is palindrome.
     * For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
     */
    public String longest(String str) {
        int length = str.length();

        boolean[][] table = new boolean[length][length];

        int maxStart = 0;
        int maxLength = 0;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < length; i++)
            table[i][i] = true;

        // check for sub-string of length 2.
        for (int i = 0; i < length - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                maxStart = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2. k is length
        for (int l = 3; l <= length; ++l) {
            // Fix the starting index
            for (int start = 0; start < length - l + 1; ++start) {
                // Get the ending index of substring from starting index start and length l
                int end = start + l - 1;

                // checking for sub-string from ith index to jth index iff str[start+1] to str[end-1] is a palindrome
                if (table[start + 1][end - 1] && str.charAt(start) == str.charAt(end)) {
                    table[start][end] = true;

                    if (l > maxLength) {
                        maxStart = start;
                        maxLength = l;
                    }
                }
            }
        }

        String result = str.substring(maxStart, maxStart + maxLength);
        return result;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     *
     * Return all possible palindrome partitioning of s.
     *
     * For example, given s = "aab",
     * Return
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     */
    public List<String> substrings(String str) {
        int length = str.length();
        boolean[][] table = new boolean[length][length];

        List<String> result = new ArrayList<>();

        for (int l = 1; l < length; l++) {
            for (int start = 0; start <= length - l; start++) {
                int end = start + l - 1;

                if (str.charAt(start) == str.charAt(end)) {
                    if (l == 1 || l == 2) {
                        table[start][end] = true;
                    } else {
                        table[start][end] = table[start + 1][end - 1];
                    }

                    if (table[start][end]) {
                        result.add(str.substring(start, end + 1));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromicSubstring().longest("zcxabba"));
        System.out.println(new PalindromicSubstring().substrings("zcxabba"));
    }
}
