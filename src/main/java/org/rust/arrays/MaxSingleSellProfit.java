package org.rust.arrays;

/**
 * Given a list of stock prices for n days, find the maximum profit with a single buy/sell activity.
 *
 * The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling in
 * those days. For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can earned by
 * buying on day 0, selling on day 3. Again buy on day 4 and sell on day 6. If the given array of prices is sorted in
 * decreasing order, then profit cannot be earned at all.
 */
public class MaxSingleSellProfit {
	public static void main(String[] args) {
		System.out.println(findProfit(new int[] {100, 180, 260, 310, 40, 535, 695}));
	}

	public static int findProfit(int[] arr) {
		if (arr.length < 2)
			return 0;

		int buyPoint = 0;
		int sellPoint = 0;

		int maxProfit = 0;

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i-1]) {
				if (buyPoint != sellPoint) {
					maxProfit += arr[sellPoint] - arr[buyPoint];
				}

				buyPoint = i;
			}

			sellPoint = i;
		}

		if (buyPoint != sellPoint) {
			maxProfit += arr[sellPoint] - arr[buyPoint];
		}

		return maxProfit;
	}
}
