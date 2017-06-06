package org.geeks.dynamic;

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
      
        return K[n][capacity];
    }
    
    public static void main(String[] args) {
        System.out.println(new Knapsack().pack(50, new int[] { 60, 100, 120 }, new int[] { 10, 20, 30 }));
    }
}
