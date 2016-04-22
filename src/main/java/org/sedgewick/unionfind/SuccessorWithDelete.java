package org.sedgewick.unionfind;

import java.util.stream.IntStream;

/**
 * Successor with delete. Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:
 * Remove x from S
 * Find the successor of x: the smallest y in S such that y≥x.
 * design a data type so that all operations (except construction) should take logarithmic time or better.
 */
public class SuccessorWithDelete {
    private int n;
    private int[] id;

    public SuccessorWithDelete(int n) {
        this.n = n;
        IntStream.range(0, n).forEach(i -> {
            id[i] = i;
        });
    }

    public void delete(int i) {
        if (i >= n - 1)
            return;

        union(i, i + 1);
    }

    public int successor(int i) {
        return root(i);
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j)
            return;

        if (i < j) {
            id[i] = j;
        } else {
            id[j] = i;
        }
    }

    private int root(int i) {
        if (id[i] == i)
            return i;
        else
            return root(id[i]);

    }

    public static void main(String[] args) {
        SuccessorWithDelete uf = new SuccessorWithDelete(10);
        uf.delete(5);
        uf.delete(6);
        System.out.println(uf.successor(5));

        uf.delete(3);
        System.out.println(uf.successor(3));

        uf.delete(4);
        System.out.println(uf.successor(3));
    }
}
