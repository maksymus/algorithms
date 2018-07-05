package org.geeks.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This is a classic interview question. Another similar problem is "merge k sorted lists".

 * This problem can be solved by using a heap. The time is O(nlog(n)).

 * Given m arrays, the minimum elements of all arrays can form a heap.
 * It takes O(log(m)) to insert an element to the heap and it takes O(1) to delete the minimum element.
 */
public class MergeKSortedArrays {
    private static class ArrayContainer {
        int[] arr;
        int pos;

        public ArrayContainer(int[] arr) {
            this.arr = arr;
        }

        public int current() {
            return arr[pos];
        }

        public boolean next() {
            return ++pos < arr.length ? true : false;
        }
    }

    public int[] merge(int[][] arrs) {
        PriorityQueue<ArrayContainer> pq = new PriorityQueue<>(Comparator.comparingInt(ac -> ac.current()));

        int length = Arrays.stream(arrs)
                .filter(arr -> arr != null).filter(arr -> arr.length > 0)
                .peek(arr -> pq.offer(new ArrayContainer(arr)))
                .map(arr -> arr.length).reduce((l1, l2) -> l1 + l2)
                .orElse(0);

        if (length == 0)
            return new int[] {};

        int[] result = new int[length];
        int pos = 0;

        while (!pq.isEmpty()) {
            ArrayContainer ac = pq.poll();
            result[pos++] = ac.current();

            if (ac.next())
                pq.offer(ac);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrs = {
                new int[]{1, 4, 7},
                new int[]{2, 3, 9}
        };

        int[] merge = new MergeKSortedArrays().merge(arrs);
        System.out.println(Arrays.toString(merge));
    }
}
