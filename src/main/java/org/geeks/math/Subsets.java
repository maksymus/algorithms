package org.geeks.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 *
 * Note: 1) Elements in a subset must be in non-descending order. 2) The solution set must not contain duplicate subsets.
 *
 * For example, given S = [1,2,3], the method returns:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {
    public List<List<Integer>> subsets(List<Integer> set) {
        List<Integer> sorted = set.stream().sorted().collect(Collectors.toList());

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(Arrays.asList());

        for (Integer i : sorted) {
            List<List<Integer>> temp = new ArrayList<>();

            for (List<Integer> subset : subsets) {
                List<Integer> newSubset = new ArrayList<>(subset);
                newSubset.add(i);
                temp.add(newSubset);
            }

            subsets.addAll(temp);
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = new Subsets().subsets(Arrays.asList(1, 2, 3));
        subsets.forEach(subset -> System.out.println(subset));
    }
}
