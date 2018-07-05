package org.geeks.dynamic;

public class EditDistance {
    public int calculate(String s, String t) {
        int[][] table = buildTable(s, t);
        return table[s.length()][t.length()];
    }

    private int[][] buildTable(String s, String t) {
        int[][] table = new int[s.length() + 1][t.length() + 1];
        
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (i == 0)
                    table[i][j] = j;
                else if (j == 0)
                    table[i][j] = i;
                else if (s.charAt(i-1) == t.charAt(j-1))
                    table[i][j] = table[i-1][j-1];
                else 
                    table[i][j] = min(table[i-1][j], table[i][j-1], table[i-1][j-1]) + 1;
            }
        }
        
        return table;
    }
    
    private int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }
    
    public static void main(String[] args) {
//        System.out.println(new EditDistance().calculate("text", "test"));
        System.out.println(new EditDistance().calculate("sunday", "saturday"));
    }
}
