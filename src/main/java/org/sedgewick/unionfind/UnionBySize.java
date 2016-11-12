package org.sedgewick.unionfind;

import java.util.Arrays;

/**
 * Develop a union-find implementation that uses the same basic strategy as
 * weighted quick-union but keeps track of tree height and always links the
 * shorter tree to the taller one. Prove a lgN upper bound on the height of the
 * trees for N sites with your algorithm.
 */
public class UnionBySize extends WeightedQuickUnion {

    private int[] height;
    
    public UnionBySize(int n) {
        super(n);

        height = new int[n];
//        IntStream.range(0, n).forEach(i -> {
//            height[i] = 1;
//        });
    }

    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j)
            return;

        if (height[i] < height[j]) {
            id[i] = j;
            height[i] = height[j] + 1;
        } else {
            id[j] = i;
            height[j] = height[i] + 1;
        }
    }
    
    public static void main(String[] args) {
        UnionBySize uf = new UnionBySize(5);
        uf.union(0, 1);
        uf.union(1, 2);
//        
        uf.union(3, 4);
        uf.union(3, 1);
        
        System.out.println(Arrays.toString(uf.id));
        System.out.println(Arrays.toString(uf.height));
    }
}
