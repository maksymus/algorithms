package org.interviewelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array {
    public static void main(String[] args) {
        List<List<Integer>> permutation = permutation(Arrays.asList(1, 2, 3, 4));
        permutation.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));
    }

    public static List<List<Integer>> permutation(List<Integer> list) {
        List<List<Integer>> permuted = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            List<Integer> prefix = new ArrayList<>();
            prefix.add(list.get(i));

            List<Integer> sublist = new ArrayList<>(list);
            sublist.remove(i);

            List<List<Integer>> permutation = permutation(sublist);
            if (permutation.isEmpty()) {
                permuted.add(prefix);
            } else {
                for (List<Integer> integers : permutation) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.addAll(prefix);
                    tmp.addAll(integers);
                    permuted.add(tmp);
                }
            }
        }

        return permuted;
    }

    /**
     * For an array of integers, give an algorithm to determine if there are three elements that sum to zero.
     * What are the time and space complexity? Generalize to the case where the sum of k elements is 0?
     */
    public static boolean sumsToZero(int[] arr, int k) {
        return sumsToZero(arr, k, 0, 1, 0);
    }

    private static boolean sumsToZero(int[] arr, int k, int currentSum, int numOfElem, int pos) {
        for (int i = pos; i < arr.length; i++) {
            int newSum = currentSum + arr[i];
            if (newSum == 0 && numOfElem == k)
                return true;

            if (numOfElem < k)
                if (sumsToZero(arr, k, newSum, numOfElem + 1, i + 1))
                    return true;
        }

        return false;
    }
    
    /**
     * Rotate an array of n elements to the right by k steps.
     * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
     * How many different ways do you know to solve this problem?
     * 
     * 1. Divide the array two parts: 1,2,3,4 and 5, 6
     * 2. Reverse first part: 4,3,2,1,5,6
     * 3. Reverse second part: 4,3,2,1,6,5
     * 4. Reverse the whole array: 5,6,1,2,3,4
     */
    public static int[] rotateKSteps(int[] arr, int k) {
        if (k <= 0 || k >= arr.length)
            return arr;
        
        arr = reverse(arr, 0, arr.length - k);
        arr = reverse(arr, arr.length - k, arr.length);
        arr = reverse(arr, 0, arr.length);
        
        return arr;
    }
    
    private static int[] reverse(int[] arr, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }
}

