package org.interviewelements;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Heaps {

    /**
     * Problem 10.1, pg. 80: Design an algorithm that takes a set of files containing stock trade information in sorted
     * order, and writes a single file containing the lines appearing in the individual files sorted in sorted order.
     */
    public static List<Integer> mergeSortedIntegers(List<List<Integer>> sortedLists) {
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> pq = new PriorityQueue<>(
                (o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
        int idx[] = new int[sortedLists.size()];

        for (int i = 0; i < sortedLists.size(); i++) {
            List<Integer> list = sortedLists.get(i);
            if (list.size() > 0)
                pq.add(new AbstractMap.SimpleEntry<>(list.get(0), i));

            idx[i] = 1;
        }

        List<Integer> ints = new ArrayList<>();

        while (!pq.isEmpty()) {
            AbstractMap.SimpleEntry<Integer, Integer> top = pq.poll();
            ints.add(top.getKey());

            Integer listIdx = top.getValue();
            List<Integer> list = sortedLists.get(listIdx);

            if (list.size() > idx[listIdx])
                pq.offer(new AbstractMap.SimpleEntry<>(list.get(idx[listIdx]++), listIdx));
        }

        return ints;
    }
}
