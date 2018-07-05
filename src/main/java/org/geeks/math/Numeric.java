package org.geeks.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Numeric {

    /**
     * Given a collection of numbers, return all possible permutations.

     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public List<List<Integer>> permutation(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        //start from an empty list
        result.add(new ArrayList<>());

        for (int i = 0; i < list.size(); i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<>();

            for (List<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, list.get(i));

                    ArrayList<Integer> temp = new ArrayList<>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    l.remove(j);
                }
            }

            result = new ArrayList<>(current);
        }

        return result;
    }

    public List<List<Integer>> permutation(List<Integer> prefix, List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        if (list.isEmpty())
            result.add(prefix);

        for (int i = 0; i < list.size(); i++) {
            List<Integer> sublist = new ArrayList<>(list);
            Integer val = sublist.remove(i);

            List<Integer> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(val);

            result.addAll(permutation(newPrefix, sublist));
        }

        return result;
    }

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * For example, if n = 4 and k = 2, a solution is:
     *
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public List<List<Integer>> combination(int n, int k) {
        return combination(IntStream.rangeClosed(1, n).toArray(), k);
    }

    public List<List<Integer>> combination(int[] arr, int k) {
        if (arr.length <= k)
            return Arrays.asList(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < arr.length - k + 1; i++) {
            List<Integer> prefix = Arrays.asList(arr[i]);
            results.addAll(combination(arr, k, prefix, i));
        }

        return results;
    }

    public List<List<Integer>> combination(int[] arr, int k, List<Integer> prefix, int pos) {
        if (prefix.size() == k)
            return Arrays.asList(prefix);

        List<List<Integer>> results = new ArrayList<>();

        for (int i = pos + 1; i < arr.length; i++) {
            ArrayList<Integer> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(arr[i]);
            results.addAll(combination(arr, k, newPrefix, i));
        }

        return results;
    }

    public static void main(String[] args) {
        Numeric numeric = new Numeric();

        System.out.println("permutations =============================");
//        List<List<Integer>> permutation = new Numeric().permutation(new ArrayList<>(), Arrays.asList(1, 2, 3));
        List<List<Integer>> permutation = numeric.permutation(Arrays.asList(1, 2, 3));
        permutation.forEach(perm -> System.out.println(perm));

        System.out.println("\ncombinations =============================");
        List<List<Integer>> combination = numeric.combination(4, 4);
        combination.forEach(comb -> System.out.println(comb));

    }
}
