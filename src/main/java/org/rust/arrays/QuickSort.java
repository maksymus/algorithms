package org.rust.arrays;

import java.util.Arrays;

/**
 * Given an integer array, sort it in ascending order using quicksort.
 */
public class QuickSort {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(sort(
				new int[] { 6, 8, 6, 7, 6, 6, 6, 3, 5, 7, 9, 2, 3, 4, 7, 9, 0, 1, 2, 5, 3})));
	}

	public static int[] sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
		return arr;
	}

	public static void sort(int[] arr, int start, int end) {
		if (start >= end)
			return;

		pair index = partition(arr, start, end);
		sort(arr, start, index.from - 1);
		sort(arr, index.to + 1, end);
	}

	private static pair partition(int[] arr, int start, int end) {
		int pivot = arr[start + (end - start) / 2];

		int lessIdx = start;
		int sameIdx = start;
		int moreIdx = end;

		while (sameIdx <= moreIdx) {
			if (arr[sameIdx] < pivot) {
				swap(arr, sameIdx, lessIdx);
				lessIdx++;
				sameIdx++;
			} else if (arr[sameIdx] == pivot) {
				sameIdx++;
			} else if (arr[sameIdx] > pivot) {
				swap(arr, sameIdx, moreIdx);
				moreIdx--;
			}
		}

		return new pair(lessIdx, moreIdx);
	}

	private static void swap(int[] arr, int i, int j) {
		if (arr[i] == arr[j])
			return;

		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static class pair {
		public int from;
		public int to;

		public pair(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public String toString() {
			return "pair{" +
					"from=" + from +
					", to=" + to +
					'}';
		}
	}
}
