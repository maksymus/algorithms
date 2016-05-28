package org.interviewelements;

import java.util.Comparator;
import java.util.List;

public class BSearch {
    /**
     * Problem 11.1 : Write a method that takes a sorted array A and a key k and returns
     * the index of the first occurrence of k in A. Return -1 if k does not appear in A.
     */
    public static int bsearch(int[] arr, int k) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (k == arr[mid]) {
                return mid;
            } else if (k > arr[mid]) {
                left = mid + 1;
            } else if (k < arr[mid]) {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Problem 11.2: Design an efficient algorithm that takes a sorted array A and akeyk,
     * and finds the index of the first occurrence an element larger than k; return -1 if every element
     * is less than or equal to k.
     */
    public static int bsearchFirstLargest(int[] arr, int k) {
        int left = 0, right = arr.length - 1, res = -1;

        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (k >= arr[mid]) {
                left = mid + 1;
            } else if (k < arr[mid]) {
                right = mid - 1;
                res = mid;
            }
        }

        return res;
    }

    /**
     * Problem 11.7: You are working in the finance office for ABC corporation. The total payroll expense
     * last year was $S. This year, the corporation needs to cut payroll expenses to $S'. The
     * chief executive officer wants to put a cap a on salaries. Every employee who earned
     * more than $cr last year will be paid $CT this year; employees who earned less than $CT
     * will see no change in their salary.
     * For example, given five employees with salaries $90, $30, $100, $40, and $20, and
     * S' = 210, then 60 is a suitable value for a.
     */
    public static double completionSearch(List<Double> salaries, double budget) {
        salaries.sort(Comparator.naturalOrder());

        double remainingBudget = budget;

        for (int i = 0; i < salaries.size(); i++) {
            double split = remainingBudget / (salaries.size() - i);

            if (Double.compare(split, salaries.get(i)) <= 0)
                return split;

            remainingBudget -= salaries.get(i);
        }

        return 0;
    }

    /**
     * Problem 11.9: Implement a function which takes as input a floating point variable x and returns sqrt(x)
     */
    public static Double sqrt(double x) {
        if (x < 0)
            throw new RuntimeException("sqrt fro x < 0");

        double estimated = x / 2.;

        while (Math.abs(x - estimated * estimated) > 0.0000001) {
            estimated = (estimated + x / estimated) / 2.;
        }

        return estimated;
    }
}
