package org.rust.string;

/**
 * Given a string find all substrings that are palindromes.
 *
 * Input: str = "abaaa"
 * Output:  Below are 5 palindrome sub-strings
 * a
 * aa
 * aaa
 * aba
 * b
 *
 *
 * Input: str = "geek"
 * Output:  Below are 4 palindrome sub-strings
 * e
 * ee
 * g
 * k
 *
 *     x p o p a p o p y
 *   0 0 0 0 0 0 0 0 0 0
 * a 0
 * a 0
 * a 0
 * b 0
 * a 0
 */
public class PalindromeSubstrings {
    public static void main(String[] args) {
        palindrome("asxpomamopy");
    }

    public static void palindrome(String str) {
        int length = str.length();
        int[][] dp = new int[length + 1][length + 1];

        char[] chars = str.toCharArray();

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                if (chars[i-1] == chars[length-j]) {
                    dp[i][j] = 1;
                }
            }
        }

        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
