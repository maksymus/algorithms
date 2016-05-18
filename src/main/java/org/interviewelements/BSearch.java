package org.interviewelements;

public class BSearch {
    /**
     * Problem 11.1 : Write a method that takes a sorted array A and a key k and returns
     * the index of the first occurrence of k in A. Return -1 if k does not appear in A.
     */
    public static int bsearch(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (k == arr[mid]) {
                return mid;
            } else if (k > arr[mid]) {
                left = mid + 1;
            } else if (k < arr[mid]) {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Problem 11.2, pg. 86 : Design an efficient algorithm that takes a sorted array A and akeyk,
     * and finds the index of the first occurrence an element larger than k; return -1 if every element
     * is less than or equal to k.
     */
    public static int bsearchFirstLargest(int[] arr, int k) {
        int left = 0, right = arr.length - 1, res = -1;

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (k >= arr[mid]) {
                left = mid + 1;
            } else if (k < arr[mid]) {
                right = mid - 1;
                res = mid;
            }
        }

        return res;
    }
}
