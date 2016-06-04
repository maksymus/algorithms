package org.interviewelements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorting {
    public static class Person {
        int key;
        public Person(int key) { this.key = key; }

        @Override
        public String toString() {
            return "Person{" +
                    "key=" + key +
                    '}';
        }
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

    private static <T> void swap(T[] arr, int from , int to) {
        T tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }
}
