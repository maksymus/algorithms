package org.sedgewick.quicksort;

import java.util.Arrays;

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
    private int[] arr;
    private int m;

    public DecimalDominants(int[] arr, int m) {
        this.m = m;
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    private void quickselect() {

    }
    
    private void partition(int pos) {
        partition(pos, 0, arr.length - 1);
    }

    // 3 way partitioning as described by Robert Sedgewick
    private int partition(int pos, int start, int end) {
        int pivot = arr[pos];
        int low = start, mid = start, hi = end;
        
        while (mid <= hi) {
            if (arr[mid] < pivot)
                swap(low++, mid++);
            else if (arr[mid] > pivot) 
                swap(hi--, mid);
            else
                mid++;
        }
        
        return mid;
    }
    
    // standard partitioning 
    private int partition(int low, int high) {
        int pivot = arr[high];
        
        int pos = low;
        
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                swap(pos++, i);
            }
        }
        
        swap(pos, high);
        
        return pos;
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int idx = 8;
        
        int[] arr = new int[] { 7, 3, 7, 5, 1, 2, 7, 8, 2, 7 };
//        int[] arr = new int[] { 1, 3, 7, 5, 1, 2, 7, 8, 2, 7 };
        DecimalDominants dd = new DecimalDominants(arr, 10);
        dd.partition(idx);
        System.out.println("pivot: " + arr[idx] + " " + Arrays.toString(dd.arr));
        
//        for (int i = 0; i < 10; i++) {
//            int[] arr = new int[] { 1, 7, 7, 5, 1, 2, 7, 8, 2, 3 };
//            DecimalDominants dd = new DecimalDominants(arr, 10);
//            dd.partition(i);
//            System.out.println("pivot: " + arr[i] + " " + Arrays.toString(dd.arr));
//        }
//        dd.partition(5);
//        System.out.println(Arrays.toString(dd.arr));
//        dd.partition(8);
//        System.out.println(Arrays.toString(dd.arr));


//        1, 3, 7, 5, 1, 2, 7, 8, 2, 7
//        1, 3, 2, 5, 7, 2, 7, 8, 7, 1
    }
}
