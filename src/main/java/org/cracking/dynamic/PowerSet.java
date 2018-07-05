package org.cracking.dynamic;

import org.interviewelements.Array;

import java.util.*;

/**
 * Write a method to return all subsets of a set.
 *
 * P(0) = {}
 * P(1) = {}, {a1}
 * P(2) = {}, {a1}, {a2}, {a1,a2}
 * P(3) = {}, {a1}, {a2}, {a3}, {a1,a2}, {a1,a3}, {a2,a3}, {a1,a2,a3}
 */
public class PowerSet {

    public static void main(String[] args) {
        List<Set<Integer>> powerSet = buildPowerSet(new HashSet<>(Arrays.asList(1, 2, 3, 4)));
        powerSet.forEach(set -> System.out.println(set));
    }

    public static <T> List<Set<T>> buildPowerSet(Set<T> set) {
        List<Set<T>> powerSet = new ArrayList<>();
        powerSet.add(new HashSet<>());

        for (T elem: set) {
            List<Set<T>> subsets = new ArrayList<>();

            for (Set<T> subset: powerSet) {
                Set<T> newSet = new HashSet<>(subset);
                newSet.add(elem);
                subsets.add(newSet);
            }

            powerSet.addAll(subsets);
        }

        return powerSet;
    }
}
