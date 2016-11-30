package org.geeks;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
 * So a string of length n has 2^n different possible subsequences.
 *
 * It is a classic computer science problem, the basis of diff (a file comparison program that outputs the 
 * differences between two files), and has applications in bioinformatics.
 * 
 * Dynamic.
 */
public class LongestCommonSubsequence {
    public String print(String a, String b) {
        int[][] l = lcs(a, b);
        
        String seq = "";

        for (int i = a.length(), j = b.length(); i > 0 && j > 0; ) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                seq = a.charAt(i - 1) + seq;
                i--; j--;
            } else if (l[i-1][j] > l[i][j-1]) {
                i--;
            } else {
                j--;
            }
            
        }
        
        return seq;
    }
    
    public int lenght(String a, String b) {
        int[][] l = lcs(a, b);
        return l[a.length()][b.length()];
    }
    
    private int[][] lcs(String a, String b) {
        int[][] l = new int[a.length() + 1][b.length() + 1];
        
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 || j == 0) {
                    l[i][j] = 0;
                } else if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    l[i][j] = l[i - 1][j - 1] + 1;
                } else {
                    l[i][j] = Math.max(l[i - 1][j], l[i][j - 1]);
                }
            }
        }
        
        return l;
    }
    
    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().lenght("AGGTAB", "GXTXAYB"));
        System.out.println(new LongestCommonSubsequence().print("AGGTAB", "GXTXAYB"));
    }
}
