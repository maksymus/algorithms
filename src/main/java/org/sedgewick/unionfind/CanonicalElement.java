package org.sedgewick.unionfind;

import java.util.stream.IntStream;

/**
 * Add a method find() to the union-find data type so that find(i) returns the largest element in the connected
 * component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.
 * For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of
 * the four elements in the connected components.
 */
public class CanonicalElement extends WeightedQuickUnion {
    private int[] canonicals;

    public CanonicalElement(int n) {
        super(n);

        canonicals = new int[n];
        IntStream.range(0, n).forEach(i -> canonicals[i] = i);
    }

    public void union(int p, int q) {
      super.union(p, q);
      canonicals[root(p)] = Math.max(canonicals[root(p)], Math.max(p, q));
    }

    public int find(int i) {
        return canonicals[root(i)];
    }

    public static void main(String[] args) {
        CanonicalElement uf = new CanonicalElement(6);
        uf.union(0, 5);
        uf.union(1, 5);
        uf.union(2, 1);
        uf.union(3, 1);

        System.out.println(uf.find(1));
        System.out.println(uf.find(4));
    }
}
