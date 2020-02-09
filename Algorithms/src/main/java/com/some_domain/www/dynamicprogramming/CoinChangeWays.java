package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/9/2020 10:38 AM
 * This class demonstrates how to find the number of ways for making change for given sum
 */
//Reference : https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/
public class CoinChangeWays {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 2, 3};
        int givenSum = 4;

        CoinChangeWays instance = new CoinChangeWays();
        int numberOfWaysToGetChange = instance.countNumberOfWaysForMakingChangeRecursive(coins, givenSum);
        System.out.println("Number of ways to get change for " + givenSum + " : " + numberOfWaysToGetChange + ". TC is exponential.");

        numberOfWaysToGetChange = instance.countNumberOfWaysForMakingChangeRecursiveAlternate(coins, coins.length - 1, givenSum);
        System.out.println("Number of ways to get change for " + givenSum + " : " + numberOfWaysToGetChange + ". TC is exponential.");

        numberOfWaysToGetChange = instance.countNumberOfWaysForMakingChange_DP_TopDown(coins, coins.length - 1, givenSum, new LinkedHashMap<>());
        System.out.println("Number of ways to get change for " + givenSum + " : " + numberOfWaysToGetChange + ". TC is O(N*S) and SC is O(N*S)");

        numberOfWaysToGetChange = instance.countNumberOfWaysForMakingChange_DP_BottomUp(coins, coins.length, givenSum);
        System.out.println("Number of ways to get change for " + givenSum + " : " + numberOfWaysToGetChange + ". TC is O(N*S) and SC is O(N)");
    }

    /*
        Problem : duplicate permutations
     */
    public int countNumberOfWaysForMakingChangeRecursive(int[] array, int givenSum) {
        if (givenSum == 0)
            return 1;

        if (givenSum < 0)
            return 0;

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result + countNumberOfWaysForMakingChangeRecursive(array, givenSum - array[i]);
        }
        return result;
    }

    public int countNumberOfWaysForMakingChangeRecursiveAlternate(int[] array, int n, int givenSum) {
        if (givenSum == 0)
            return 1;

        if (givenSum < 0 || n < 0)
            return 0;

        int exclude = countNumberOfWaysForMakingChangeRecursiveAlternate(array, n - 1, givenSum);
        int include = countNumberOfWaysForMakingChangeRecursiveAlternate(array, n, givenSum - array[n]);

        return exclude + include;
    }

    public int countNumberOfWaysForMakingChange_DP_TopDown(int[] array, int n, int givenSum, Map<String, Integer> map) {
        if (givenSum == 0)
            return 1;

        if (givenSum < 0 || n < 0)
            return 0;

        String key = n + "-" + givenSum;
        if (!map.containsKey(key)) {
            int exclude = countNumberOfWaysForMakingChange_DP_TopDown(array, n - 1, givenSum, map);
            int include = countNumberOfWaysForMakingChange_DP_TopDown(array, n, givenSum - array[n], map);
            map.put(key, exclude + include);
        }
        return map.get(key);
    }

    //https://www.geeksforgeeks.org/coin-change-dp-7/  Simplified version for method 2
    public int countNumberOfWaysForMakingChange_DP_BottomUp(int array[], int n, int givenSum) {
        int table[] = new int[givenSum + 1];
        table[0] = 1;

        // Pick all coins one by one and update the table[] values
        // after the index greater than or equal to the value of the
        // picked coin
        for (int i = 0; i < n; i++)
            for (int j = array[i]; j <= givenSum; j++)
                table[j] += table[j - array[i]];

        return table[givenSum];
    }
}
