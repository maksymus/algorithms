package org.geeks;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle. For example, when k = 3, the row is [1,3,3,1].
 */
public class PascalTriangle {

    public static void main(String[] args) {
        System.out.println(new PascalTriangle().row(5));
    }
    
    public List<Integer> row(int k) {
        List<Integer> row = new ArrayList<>();

        if (k < 0)
            return row;
        
        row.add(1);

        for (int i = 1; i <= k; i++) {
            row.add(1);
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        
        return row;
    }
}
