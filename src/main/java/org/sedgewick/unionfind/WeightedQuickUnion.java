package org.sedgewick.unionfind;

import java.util.stream.IntStream;

public class WeightedQuickUnion {
    protected int[] id;
    protected int[] sz;

    public WeightedQuickUnion(int n) {
        id = new int[n];
        sz = new int[n];

        IntStream.range(0, n).forEach(i -> {
            id[i] = i;
            sz[i] = 1;
        });
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j)
            return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    final protected int root(int i) {
        if (id[i] == i)
            return i;
        else
            return root(id[i]);
    }

    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(6);
        uf.union(0, 5);
        uf.union(1, 5);
        uf.union(2, 1);
        uf.union(3, 1);
        
        uf.union(4, 1);

        System.out.println(uf.connected(0, 2));
        System.out.println(uf.connected(0, 4));
    }
}
