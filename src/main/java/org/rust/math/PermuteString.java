package org.rust.math;

/**
 * Implement a method to print all permutations of a given string.
 */
public class PermuteString {
    public static void main(String[] args) {
        permute("1234", "");
    }

    public static void permute(String string, String prefix) {
        if (string.isEmpty())
            System.out.println(prefix);

        for (int i = 0; i < string.length(); i++) {
            String newPrefix = prefix + String.valueOf(string.charAt(i));
            String subString = string.substring(0, i) + string.substring(i + 1, string.length());

            permute(subString, newPrefix);
        }
    }
}
