package org.sedgewick.mergesort;

import java.util.Arrays;

/**
 * Counting inversions.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 */
public class CountInversion {
    public int countInversions(int[] arr) {
        return sort(arr);
    }
    
    public int sort(int[] objs) {
        if (objs.length <= 1)
            return 0;

        int mid = objs.length / 2;

        int[] left = Arrays.copyOfRange(objs, 0, mid);
        int[] right = Arrays.copyOfRange(objs, mid, objs.length);

        int linv = sort(left);
        int rinv = sort(right);

        return linv + rinv + merge(objs, left, right);
    }

    private int merge(int[] objs, int[] a, int[] b) {
        int i = 0, j = 0, invCount = 0;

        for (int k = 0; k < a.length + b.length; k++) {
            if (i >= a.length) {
                System.arraycopy(b, j, objs, k, b.length - j);
                break;
            }

            if (j >= b.length) {
                System.arraycopy(a, i, objs, k, a.length - i);
                break;
            }
            
            if (a[i] <= b[j]) {
                objs[k] = a[i++];
            } else {
                objs[k] = b[j++];
                invCount += a.length - i;
            }
        }
        
        return invCount;
    }
    
    public static void main(String[] args) {
//        int[] arr = new int[] { 4, 3, 2, 1};
        int arr[] = { 1, 20, 6, 4, 5 };
        int res = new CountInversion().countInversions(arr);
        System.out.println(res + ": " + Arrays.toString(arr));
    }
}
