package org.geeks;

import java.util.Deque;
import java.util.LinkedList;

/**
 * http://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example, given height = [2,1,5,6,2,3], return 10.
 */
public class LargestRectangle {

    public static void main(String[] args) {
//        int[] height = {2, 1, 5, 6, 2, 3};
        int[] height = {6, 2, 5, 4, 5, 1, 6};

        System.out.println(brute(height));
        System.out.println(optim(height));
    }

    public static int optim(int[] height) {
        Deque<Integer> stack = new LinkedList<>();

        int max = 0;

        for (int i = 0; i < height.length; ) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();

                // Calculate the area with hist[tp] stack as smallest bar
                int areaWithTop = height[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);

                // update max area, if needed
                if (max < areaWithTop)
                    max = areaWithTop;
            }
        }

        while (!stack.isEmpty()) {
            int i = height.length;
            int top = stack.pop();

            // Calculate the area with hist[tp] stack as smallest bar
            int areaWithTop = height[top] * (stack.isEmpty() ? i : i - stack.peek() - 1);

            // update max area, if needed
            if (max < areaWithTop)
                max = areaWithTop;
        }

        return max;
    }

    public static int brute(int[] height) {
        int max = 0;

        for (int i : height) {
            int maxLocal = 0;

            for (int j : height) {
                if (j >= i) {
                    maxLocal += i;
                } else {
                    max = maxLocal > max ? maxLocal : max;
                    maxLocal = 0;
                }
            }

            max = maxLocal > max ? maxLocal : max;
        }

        return max;
    }
}
