package org.cracking.arrays;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 * is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
 *
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations: "taco cat", "atco eta", etc.)
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        System.out.println(new PalindromePermutation().isPalindromePermutation("Tact Coa"));
    }

    public boolean isPalindromePermutation(String str) {
        String trimmed = prepareString(str);
        int odds = countOdds(trimmed);
        return checkZeroOrOneBitSet(odds);
    }

    private String prepareString(String str) {
        return str.replaceAll(" ", "").trim().toLowerCase();
    }

    private int countOdds(String str) {
        int odds = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            odds ^= 1 << ch - 'a';
        }

        return odds;
    }

    private boolean checkZeroOrOneBitSet(int odds) {
        // 0100 - 1 == 0011
        // 0100 & 0011 == 0
        return (odds & (odds - 1)) == 0;
    }
}
