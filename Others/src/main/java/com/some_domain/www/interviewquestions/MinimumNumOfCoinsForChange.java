package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 01-10-2022 03:56 pm
 * <p>
 * Reference : https://www.youtube.com/watch?v=1R0_7HqNaW0&ab_channel=KevinNaughtonJr.
 */
public class MinimumNumOfCoinsForChange {

    public static void main(String[] args) {

        MinimumNumOfCoinsForChange instance = new MinimumNumOfCoinsForChange();
        int[] coins = {1, 2, 5, 10, 20};
        int amount = 52;
        int minimumNumberOfCoins = instance.getMinimumNumberOfCoins(coins, amount);
        System.out.println("Minimum number of coins to get the amount '" + amount + "' is " + minimumNumberOfCoins);
        System.out.println("Time complexity is O(N*V) where N is the number of coins and V is the amount and space complexity is O(V)");
    }

    private int getMinimumNumberOfCoins(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
