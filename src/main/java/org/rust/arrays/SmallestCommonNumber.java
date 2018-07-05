package org.rust.arrays;

/**
 * Given three integer arrays sorted in ascending order, find the smallest number that is common in all three arrays.
 */
public class SmallestCommonNumber {
    public static void main(String[] args) {
        Integer integer = find(new int[]{1, 2, 3}, new int[]{2, 3}, new int[]{3});
        System.out.println(integer);
    }

    public static Integer find(int[] arr1, int[] arr2, int[] arr3) {
        int idx1 = 0, idx2 = 0, idx3 = 0;

        while (idx1 < arr1.length && idx2 < arr2.length && idx3 < arr3.length) {
            int elem1 = arr1[idx1];
            int elem2 = arr2[idx2];
            int elem3 = arr3[idx3];

            if ((elem1 == elem2) && (elem2 == elem3)) {
                return elem1;
            }

            int min = Math.min(elem1, Math.min(elem2, elem3));
            if (min == elem1) {
                idx1++;
            } else if (min == elem2) {
                idx2++;
            } else {
                idx3++;
            }
        }

        return null;
    }
}
