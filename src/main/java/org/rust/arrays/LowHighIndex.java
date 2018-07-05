package org.rust.arrays;

/**
 * Given a sorted array of integers, return the low and high index of the given key. Return -1 if not found.
 * The array length can be in millions with lots of duplicates.
 *
 * Input : arr[] = {1, 3, 5, 5, 5, 5 ,67, 123, 125}
 * x = 5
 * Output : First Occurrence = 2
 * Last Occurrence = 5
 *
 * Input : arr[] = {1, 3, 5, 5, 5, 5 ,7, 123 ,125 }
 * x = 7
 * Output : First Occurrence = 6
 * Last Occurrence = 6
 */
public class LowHighIndex {
    public static void main(String[] args) {
        search(new int[] {1, 3, 5, 5, 5, 5 ,67, 123, 125}, 5);
    }

    public static void search(int[] arr, int key) {
        int start = searchStart(arr, key);
        int end = searchEnd(arr, key);

        System.out.println(String.format("start: %d end: %d", start, end));
    }

    private static int searchStart(int[] arr, int key) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int elem = arr[mid];

            if (elem > key) {
                end = mid - 1;
            } else if (elem < key) {
                start = mid + 1;
            } else {
                if (mid == 0 || arr[mid-1] != key)
                    return mid;

                end = mid - 1;
            }
        }

        return -1;
    }

    private static int searchEnd(int[] arr, int key) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int elem = arr[mid];

            if (elem > key) {
                end = mid - 1;
            } else if (elem < key) {
                start = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid+1] != key)
                    return mid;

                start = start - 1;
            }
        }

        return -1;
    }
}
