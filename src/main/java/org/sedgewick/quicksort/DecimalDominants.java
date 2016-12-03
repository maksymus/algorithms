package org.sedgewick.quicksort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Decimal dominants.
 * Given an array with N keys, design an algorithm to find all values that occur more than N/10 times.
 * The expected running time of your algorithm should be linear.
 *
 * Heavy Hitters
 * Another problem I want to mention is called Decimal Dominants, also known as “heavy hitters”.
 * In an unordered list, find all the elements that dominate, i, e, that occur > N / m percent, where N is the length of the
 * list and m = 10. If m = 2, then we are looking for the item that is a majority, if there is one. This problem (for any m)
 * can be solved by the Boyer-Moore Majority Voting algorithm (which is really cool). By only using m - 1 counters
 * (no matter how large N is), the algorithm generates candidates that could dominate. A second pass is needed per
 * candidate to count occurrences to determine if they in fact do dominate.
 *
 * BUT, a completely different algorithm can be used to solve this problem based on partitioning.
 * (quicksort partitions a list to get 1 item in place). Since you know that a dominant item is > N/10, you know that its
 * final position in a sorted list must be on some N/10 boundary. (But you don’t need to sort the list!) For example,
 * if 5 dominates a list of N = 100, then it must occur at least 11 times and a 5 must be in one of the
 * positions 9, 19, 29, …, 99. Use partitioning to find the items that sit on N/10 boundaries.
 * (This use of partitioning is called quickselect.)  The result from quickselect is the
 * lower and upper indices of the item so the difference between upper index and lower
 * index indicates whether the item dominates. In general, for any N and any m, the number of
 * boundary positions on which to call quickselect is at most m - 1.
 */
class DecimalDominants {

    /**
     * Find all element appearing more than <code>m</code> times.
     */
    public List<Integer> findDominants(int[] arr, int m) {
        int steps = arr.length / m; 
        IntStream.range(0, steps).forEach(step -> quickselect(arr, step * m));
        return Arrays.asList();
    }
    
    /**
     *  Find k-th element smallest element of array.  
     */
    public int quickselect(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        
        while (lo < hi) {
            int pivot = partition(arr, lo, hi);
            if (pivot == k) 
                return arr[pivot];
            if (pivot < k) 
                lo = pivot + 1;
            if (pivot > k)
                hi = pivot - 1;
        }
        
        return arr[k];
    }
    
    /**
     * Quicksort 
     */
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }
    
    private void sort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        
        int partition = partition(arr, lo, hi);
        sort(arr, lo, partition - 1);
        sort(arr, partition + 1, hi);
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high + 1; 
        
        while (true) {
            while (arr[++i] <= pivot)
                if (i == high) break;
            
            while (arr[--j] > pivot)
                if (j == low) break;
            
            if (i >= j)
                break;

            swap(arr, i, j);
        }
        
        swap(arr, low, j);
        
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        
//        int[] arr = new int[] { 7, 3, 7, 5, 1, 2, 7, 8, 2, 7 }; // [1, 2, 2, 3, 5, 7, 7, 7, 7, 8]
//        int[] arr = new int[] { 1, 3, 7, 5, 1, 2, 7, 8, 2, 7 };
        int[] arr = new int[] { 3, 1, 7, 2, 4, 0, 5, 9, 6, 8 };
        
        DecimalDominants dd = new DecimalDominants();
        System.out.println(dd.quickselect(arr, arr.length / 2));
    }
}
