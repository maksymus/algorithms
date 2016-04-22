package org.interviewelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array {
    public static void main(String[] args) {
        List<List<Integer>> permutation = permutation(Arrays.asList(1, 2, 3, 4));
        permutation.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));
    }

    public static List<List<Integer>> permutation(List<Integer> list) {
        List<List<Integer>> permuted = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            List<Integer> prefix = new ArrayList<>();
            prefix.add(list.get(i));

            List<Integer> sublist = new ArrayList<>(list);
            sublist.remove(i);

            List<List<Integer>> permutation = permutation(sublist);
            if (permutation.isEmpty()) {
                permuted.add(prefix);
            } else {
                for (List<Integer> integers : permutation) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.addAll(prefix);
                    tmp.addAll(integers);
                    permuted.add(tmp);
                }
            }
        }

        return permuted;
    }
}

