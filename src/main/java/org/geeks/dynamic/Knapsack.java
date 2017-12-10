package org.geeks.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    public int packrec(int capacity, int[] values, int[] weights) {
        return packrec(capacity, values, weights, 0);
    }
    
    private int packrec(int capacity, int[] values, int[] weights, int i) {
        if (i >= values.length)
            return 0;
        
        if (weights[i] > capacity)
            return 0;
        
        return Math.max(values[i] + packrec(capacity - weights[i], values, weights, i + 1), 
                packrec(capacity, values, weights, i + 1));
    }

    public int pack(int capacity, int[] values, int[] weights) {
        int[][] table = _pack(capacity, values, weights);
        return table[values.length][capacity];
    }

    private int[][] _pack(int capacity, int[] values, int[] weights) {
        int n = values.length;
        
        int i, w;
        int K[][] = new int[n+1][capacity+1];
      
        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weights[i-1] <= w)
                    K[i][w] = Math.max(values[i-1] + K[i-1][w-weights[i-1]], K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }
      
        return K;
    }

    private List<Integer> items(int capacity, int[] values, int[] weights) {
        int[][] table = _pack(capacity, values, weights);

        List<Integer> items = new ArrayList<>();

        int item = values.length;
        while (capacity > 0) {
            while (item > 0 && table[item][capacity] == table[item-1][capacity])
                item--;

            capacity = capacity - weights[item-1];
            if (capacity >= 0)
                items.add(item - 1);
        }

        return items;
    }
    
    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        System.out.println(knapsack.pack(50, new int[] { 60, 100, 120 }, new int[] { 10, 20, 30 }));

        int[][] ints = knapsack._pack(8, new int[]{5, 5, 2, 4, 3, 4, 3, 7}, new int[]{4, 2, 6, 5, 3, 5, 2, 5});

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(ints[i][j] + "\t");
            }
            System.out.println();
        }

        List<Integer> items = knapsack.items(8, new int[]{5, 5, 2, 4, 3, 4, 3, 7}, new int[]{4, 2, 6, 5, 3, 5, 2, 5});
        System.out.println(items);
    }
}
