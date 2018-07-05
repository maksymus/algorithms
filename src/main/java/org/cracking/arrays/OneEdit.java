package org.cracking.arrays;

/**
 * There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale,  ple  -> true
 * pales, pale -> true
 * pale,  bale -> true
 * pale,  bake -> false
 *
 * Edit distance.
 */
public class OneEdit {
    public static void main(String[] args) {
//        System.out.println(isOneEditSimple("pale", "ple"));
//        System.out.println(isOneEditSimple("pales", "pale"));
        System.out.println(isOneEditSimple("pale", "bale"));
//        System.out.println(isOneEditSimple("pale", "bake"));
    }

    public static boolean isOneEditSimple(String str1, String str2) {
        if (Math.abs(str1.length() - str2.length()) > 1)
            return false;

        boolean isAdd = str1.length() != str2.length();

        String shorter = str1.length() < str2.length() ? str1 : str2;
        String longer = str1.length() < str2.length() ? str2 : str1;

        for (int i = 0, skips = 0, replaces = 0; i < shorter.length(); i++) {
            if (shorter.charAt(i) != longer.charAt(i + skips)) {
                if (isAdd) {
                    skips++;
                    i--;
                } else {
                    replaces++;
                }

                if (skips > 1 || replaces > 1)
                    return false;
            }
        }

        return true;
    }

    public static boolean isOneEdit(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
            matrix[i][0] = i;

        for (int j = 0; j <= str2.length(); j++)
            matrix[0][j] = j;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1];
                } else {
                    matrix[i][j] = min(matrix[i-1][j], matrix[i][j-1], matrix[i-1][j-1]) + 1;
                }
            }
        }

        return matrix[str1.length()][str2.length()] <= 1;
    }

    private static int min(int i1, int i2, int i3) {
        return Math.min(Math.min(i1, i2), i3);
    }
}
