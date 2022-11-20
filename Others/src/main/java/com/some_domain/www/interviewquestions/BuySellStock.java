package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 20-11-2022 11:49 am
 */
public class BuySellStock {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        BuySellStock instance = new BuySellStock();
        int maxProfit = instance.findMaxProfit(prices);
        System.out.println("Max profit is : " + maxProfit);
    }

    private int findMaxProfit(int[] prices) {
        int maxProfit = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            } else if (prices[i] - minValue > maxProfit) {
                maxProfit = prices[i] - minValue;
            }
        }
        return maxProfit;
    }
}
