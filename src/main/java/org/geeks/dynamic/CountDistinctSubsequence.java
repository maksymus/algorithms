package org.geeks.dynamic;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * Input  : S = banana, T = ban
 * Output : 3
 * T appears in S as below three subsequences:
 *  banana
 *  ------
 * [ban]
 * [ba  n]
 * [b  an]
 *
 * Input  : S = geeksforgeeks, T = ge
 * Output : 6
 * T appears in S as below three subsequences.
 *  geeksforgeeks
 *  -------------
 * [ge]
 * [g e]
 * [        ge]
 * [        g e]
 * [g        e]
 * [g         e]

 */
public class CountDistinctSubsequence {
    public int count(String s, String t) {
        int n = s.length();
        int m = t.length();

        // longer cannot be subsequence of shorter
        if (m > n)
            return 0;

        int[][] table = new int[m+1][n+1];

        for (int i = 0; i <= m; i++)
            table[i][0] = 0;

        // empty string T is a substring of any string S
        for (int j = 0; j <= n; j++)
            table[0][j] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If last characters match than value is obtained considering two cases.
                // a) All substrings without last character in S
                // b) All substrings without last characters in both.
                // Else value is same as the value without last character in S
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    table[i][j] = table[i][j-1] + table[i-1][j-1];
                } else {
                    table[i][j] = table[i][j-1];
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

        return table[m][n];
    }

    public static void main(String[] args) {
        int count = new CountDistinctSubsequence().count("geeksforgeeks", "ge");
        System.out.println(count);
    }
}
