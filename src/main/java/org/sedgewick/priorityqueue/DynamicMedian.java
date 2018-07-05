package org.sedgewick.priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.IntStream;

/*
 * Design a data type that supports insert in logarithmic time, find-the-median in constant time, and remove-the-median
 * in logarithmic time.
 *
 * Keep two priority queues with equal number of elements or 1 element diff (split stream in the middle).
 */
public class DynamicMedian {
    // 5 -> 5
    // 5, 3 -> 4
    // 5, 3, 7 -> 5
    // 5, 3, 7, 20 -> 6

    /* left part of stream (3, 5) */
    private PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    /* right part of stream (7, 20) */
    private PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Comparator.naturalOrder());

    public void insert(int i) {
        if (maxPriorityQueue.isEmpty() && minPriorityQueue.isEmpty()) {
            maxPriorityQueue.add(i);
        } else {
            if (maxPriorityQueue.peek() <= i)
                maxPriorityQueue.add(i);
            else {
                minPriorityQueue.add(i);
            }

            balance();
        }
    }

    public double find() {
        if (maxPriorityQueue.isEmpty() && minPriorityQueue.isEmpty())
            throw new IllegalStateException("stream is empty");

        if (maxPriorityQueue.size() == minPriorityQueue.size())
            return (maxPriorityQueue.peek() + minPriorityQueue.peek()) / 2.0;

        if (maxPriorityQueue.size() > minPriorityQueue.size())
            return maxPriorityQueue.peek();

        return minPriorityQueue.peek();
    }

    public void remove() {
        if (maxPriorityQueue.isEmpty() && minPriorityQueue.isEmpty())
            throw new IllegalStateException("stream is empty");

        if (maxPriorityQueue.size() >= minPriorityQueue.size())
            maxPriorityQueue.poll();
        else
            minPriorityQueue.poll();

        balance();
    }

    private void balance() {
        if (minPriorityQueue.size() > maxPriorityQueue.size())
            maxPriorityQueue.add(minPriorityQueue.poll());
        else if (minPriorityQueue.size() < maxPriorityQueue.size())
            minPriorityQueue.add(maxPriorityQueue.poll());
    }

    private void trace() {
        System.out.printf("min: %s, max: %s\n", Arrays.toString(minPriorityQueue.stream().sorted().toArray()),
                Arrays.toString(maxPriorityQueue.stream().sorted().toArray()));
    }

    public static void main(String[] args) {
        DynamicMedian dynamicMedian = new DynamicMedian();

        int[] ints = IntStream.generate(() -> new Random().nextInt(1000)).limit(300).toArray();
        for (int i : ints) {
            dynamicMedian.insert(i);
            System.out.println(dynamicMedian.find());
        }
    }
}
