package org.geeks.dynamic;

/**
 * The wildcard pattern can include the characters ‘?’ and ‘*’
 * ‘?’ – matches any single character
 * ‘*’ – Matches any sequence of characters (including the empty sequence)
 *
 * For example,
 *
 * Text = "baaabab",
 * Pattern = “*****ba*****ab", output : true
 * Pattern = "baaa?ab", output : true
 * Pattern = "ba*a?", output : true
 * Pattern = "a*ab", output : false
 *
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
 */
public class PatternMatching {
    public boolean match(String string, String pattern) {
        boolean[][] dp = new boolean[string.length() + 1][pattern.length() + 1];

        // empty pattern can match with empty string
        dp[0][0] = true;

        // Only '*' can match with empty string
        for (int j = 1; j <= pattern.length(); j++)
            if (pattern.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 1];

        for (int i = 1; i <= string.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                char pChar = pattern.charAt(j - 1);

                if (pChar == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pChar == '?' || pChar == string.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }

            }
        }

        return dp[string.length()][pattern.length()];
    }
}
