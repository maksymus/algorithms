package org.rust.arrays;

import java.util.Arrays;

/**
 * Given an integer array, move all elements containing '0' to the left while maintaining the order of other elements in the array.
 *
 * Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
 * Output : arr[] = {1, 2, 3, 6, 0, 0, 0};
 */
public class ShiftZeroes {
	public static void main(String[] args) {
		int[] shift = shift(new int[]{1, 2, 0, 0, 0, 3, 6});
		System.out.println(Arrays.toString(shift));
	}

	private static int[] shift(int[] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			boolean isZero = arr[i] == 0;

			swap(arr, i, i - count);

			if (isZero)
				count++;
		}

		return arr;
	}

	private static void swap(int[] arr, int i, int j) {
		if (i == j)
			return;

		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
