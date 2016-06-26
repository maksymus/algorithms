package org.interviewelements;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorting {
    public static class Person {
        int key;
        public Person(int key) { this.key = key; }
    }

    /**
     * Problem 13.4: You are given an array of n Person objects. Each Person object
     * has afield key. Rearrange the elements of the array so that Person objects with equal keys
     * appear together. The order in which distinct keys appear is not important. Your algorithm
     * must run in 0(n) time and 0(k) additional space.
     */
    public static void countingSort(Person[] persons) {
        Map<Integer, Integer> keyCounts = Arrays.stream(persons)
                .collect(Collectors.groupingBy(person -> person.key, Collectors.reducing(0, e -> 1, Integer::sum)));

        int baseOffset = 0;
        Map<Integer, Integer> keyOffsets = new HashMap<>();
        for (int key : keyCounts.keySet()) {
            keyOffsets.put(key, baseOffset);
            baseOffset += keyCounts.get(key);
        }

        Stream.iterate(persons[0], (Person person) -> {
            int offset = keyOffsets.get(person.key);
            Person tmpPerson = persons[offset];
            persons[offset] = person;
            keyOffsets.put(person.key, keyOffsets.get(person.key) + 1);
            return tmpPerson;
        }).limit(persons.length).count();

//        Person person = persons[0];
//        for (int i = 0; i < persons.length - 1; i++) {
//            int offset = keyOffsets.get(person.key);
//            Person tmpPerson = persons[offset];
//            persons[offset] = person;
//            keyOffsets.put(person.key, keyOffsets.get(person.key) + 1);
//            person = tmpPerson;
//        }
    }

    /**
     * Problem 13.5, pg. 99 : Given sorted arrays A and B of lengths n and m respectively, return
     * an array C containing elements common to A and B. The array C should be free of duplicates.
     * How would you perform this intersection if—(l.) n&m and (2.) n cm?
     */
    public static <T extends Comparable<T>> List<T> intersectArrays(List<T> a, List<T> b) {
        int aSize = a.size();
        int bSize = b.size();

        List<T> list = new ArrayList<>();
        for (int i = 0, aCounter = 0, bCounter = 0; i < aSize + bSize; i++) {
            if (aCounter >= aSize) {
                list.add(b.get(bCounter++));
            } else if (bCounter >= bSize) {
                list.add(a.get(aCounter++));
            } else {
                if (a.get(aCounter).compareTo(b.get(bCounter)) <= 0) {
                    list.add(a.get(aCounter++));
                } else {
                    list.add(b.get(bCounter++));
                }
            }
        }

        return list;
    }

    private static <T> void swap(T[] arr, int from , int to) {
        T tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }
}
