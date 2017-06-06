package org.geeks.dynamic;

public class EditDistance {
    public int calculate(String str1, String str2) {
        int[][] dp = _calculate(str1, str2);
        return dp[str1.length()][str2.length()];
    }
    
    private int[][] _calculate(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else 
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1; 
            }
        }
        
        return dp;
    }
    
    private int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }
    
    public static void main(String[] args) {
//        System.out.println(new EditDistance().calculate("text", "test"));
        System.out.println(new EditDistance().calculate("sunday", "saturday"));
    }
}
