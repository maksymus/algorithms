package org.geeks.dynamic;

public class BuySellStock {

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     */
    public int maxProfit(int[] prices) {
        int max = Integer.MIN_VALUE;

        for (int i = 0, min = Integer.MAX_VALUE; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(prices[i], min);
        }

        return max;
    }


    /**
     * The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling
     * in those days. For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can
     * earned by buying on day 0, selling on day 3. Again buy on day 4 and sell on day 6. If the given array of prices
     * is sorted in decreasing order, then profit cannot be earned at all.
     */
    public int maxProfit1(int[] prices) {
        return 0;
    }

    public static void main(String[] args) {
        BuySellStock buySellStock = new BuySellStock();
        int maxProfit = buySellStock.maxProfit(new int[]{3, 5, 2, 6, 1});
        System.out.println(maxProfit);
    }
}
