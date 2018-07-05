package org.sedgewick.sort;


/*
 * Permutation. Given two integer arrays of size N, design a subquadratic algorithm to determine whether one is a
 * permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {
    private List<Integer> as;
    private List<Integer> bs;

    public Permutation(List<Integer> as, List<Integer> bs) {
        this.as = as;
        this.bs = bs;
    }

    public boolean isPermutation() {
        if (as.size() != bs.size())
            return false;

        Collections.sort(as);
        Collections.sort(bs);

        for (int i = 0; i < as.size(); i++)
            if (!as.get(i).equals(bs.get(i)))
                return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Permutation(Arrays.asList(1, 3, 5, 2, 3), Arrays.asList(3, 3, 2, 5, 1)).isPermutation());
        System.out.println(new Permutation(Arrays.asList(1, 3, 5, 2, 3), Arrays.asList(3, 3, 2, 5, 6)).isPermutation());
    }
}