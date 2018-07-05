package org.rust.arrays;

/**
 * Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
 * Return index of element or -1 if not present.
 */
public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(find(new int[] {0, 1, 3, 5, 7, 10}, 0));
    }

    public static int find(int[] arr, int elem) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + ((end - start) >> 1);

            if (arr[mid] == elem) {
                return mid;
            } else if (arr[mid] < elem) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
