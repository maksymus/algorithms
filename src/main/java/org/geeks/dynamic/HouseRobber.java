package org.geeks.dynamic;


import java.util.ArrayList;
import java.util.List;

public class HouseRobber {
    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
     * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     */
    public int naive(int[] amounts) {
        return naive(amounts, amounts.length - 1);
    }

    private int naive(int[] amounts, int pos) {
        if (pos == 0)
            return amounts[0];

        if (pos == 1)
            return Math.max(amounts[0], amounts[1]);

        return Math.max(amounts[pos] + naive(amounts, pos - 2), naive(amounts, pos - 1));
    }

    public List<Integer> naiveList(int[] amounts) {
        return naiveList(amounts, amounts.length - 1, new ArrayList<>());
    }

    private List<Integer> naiveList(int[] amounts, int pos, List<Integer> prefix) {
        if (pos == 0) {
            prefix.add(amounts[0]);
            return prefix;
        }

        if (pos == 1) {
            prefix.add(Math.max(amounts[0], amounts[1]));
            return prefix;
        }

        List<Integer> includePrefix = new ArrayList<>(prefix);
        List<Integer> excludePrefix = new ArrayList<>(prefix);

        includePrefix.add(amounts[pos]);
        List<Integer> include = naiveList(amounts, pos - 2, includePrefix);
        List<Integer> exclude = naiveList(amounts, pos - 1, excludePrefix);

        return sum(include) >= sum(exclude) ? include : exclude;
    }

    public int dynamic(int[] amounts) {
        int amount1 = amounts[0];
        int amount2 = Math.max(amounts[0], amounts[1]);

        for (int i = 2; i < amounts.length; i++) {
            int max = Math.max(amount1 + amounts[i], amount2);
            amount1 = amount2;
            amount2 = max;
        }

        return amount2;
    }

    private int sum(List<Integer> list) {
        return list.stream().mapToInt(i -> i).sum();
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();

        int[] amounts = {6, 7, 1, 3, 8, 2, 4};
        System.out.println(houseRobber.naive(amounts));
        List<Integer> integers = houseRobber.naiveList(amounts);
        System.out.println(integers);
        System.out.println(houseRobber.dynamic(amounts));
//        Input  : hval[] = {6, 7, 1, 3, 8, 2, 4}
//        Output : 19
//        Thief will steal 6, 1, 8 and 4 from house.

//        System.out.println(houseRobber.naive(new int[] { 5, 3, 4, 11, 2 }));
//        Input  : hval[] = {5, 3, 4, 11, 2}
//        Output : 16
//        Thief will steal 5 and 11
    }
}
