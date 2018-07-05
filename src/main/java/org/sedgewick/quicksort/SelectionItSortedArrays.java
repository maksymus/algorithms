package org.sedgewick.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Selection in two sorted arrays. Given two sorted arrays a[] and b[], of sizes
 * N1 and N2, respectively, design an algorithm to find the kth largest key. The
 * order of growth of the worst case running time of your algorithm should be
 * logN, where N=N1+N2.
 * <li>Version 1: N1=N2 and k=N/2
 * <li>Version 2: k=N/2
 * <li>Version 3: no restrictions
 * https://www.quora.com/What-is-the-fastest-algorithm-for-computing-the-kth-smallest-element-in-the-union-of-two-sorted-lists-of-size-m-and-n
 */
public class SelectionItSortedArrays {
    public static void main(String[] args) {

        int[] a = { 1, 3, 5, 7, 9 };
        int[] b = { 2, 4, 6, 8, 10 };

        List<Integer> list = new ArrayList<>();
        
        Arrays.stream(a).forEach(list::add);
        Arrays.stream(b).forEach(list::add);
        
        Collections.sort(list);
        
        int kthSmallest = getKthSmallestInTwoArrays(a, b, 5);
        System.out.println(kthSmallest);
        
//        for (int i = 0; i < list.size(); i++) {
//            int k = i + 1;
//            int kthSmallest = getKthSmallestInTwoArrays(a, b, k);
//            if (kthSmallest != list.get(k - 1)) {
//                System.out.println(String.format("FAILED: found %d should be %d", 
//                        kthSmallest, list.get(k - 1)));
//            } else {
//                System.out.println("Success: kth smallest " + kthSmallest);
//            }
//        }
    }

    private static int getKthSmallestInTwoArrays(int[] A, int[] B, int k) {
        int a = A.length;
        int b = B.length;
 
        if (k < 1 || k > a + b)
            throw new IllegalArgumentException("k is not within range!");
 
        int minSizeA = Math.max(0, k - b);
        int maxSizeA = Math.min(a, k);
 
        while (minSizeA <= maxSizeA) {
            int sizeA = minSizeA + (maxSizeA - minSizeA) / 2;
            int sizeB = k - sizeA;
            
            int indexA = sizeA - 1;
            int indexB = sizeB - 1;

            int valA = (indexA < 0) ? Integer.MIN_VALUE : A[indexA];
            int valB = (indexB < 0) ? Integer.MIN_VALUE : B[indexB];
            int valANext = (sizeA >= a) ? Integer.MAX_VALUE : A[sizeA];
            int valBNext = (sizeB >= b) ? Integer.MAX_VALUE: B[sizeB];
 
            if (valA <= valBNext && valB <= valANext) {
                return Math.max(valA, valB);
            } else if (valA > valBNext) {
                maxSizeA = sizeA - 1;
            } else {
                minSizeA = sizeA + 1;
            }
        }
 
        return 0;
    }
}
