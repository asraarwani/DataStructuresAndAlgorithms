package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 15-10-2022 05:42 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/stock-buy-sell/
 */
public class StockBuyAndSell {

    public static void main(String[] args) {

        int[] array = {100, 180, 260, 310, 40, 535, 695};
        StockBuyAndSell instance = new StockBuyAndSell();
        instance.stockBuyAndSell(array);
    }


    private void stockBuyAndSell(int[] array) {
        int maxProfit = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                maxProfit += array[i] - array[i - 1];
            }
        }
        System.out.println(maxProfit);
    }
}
