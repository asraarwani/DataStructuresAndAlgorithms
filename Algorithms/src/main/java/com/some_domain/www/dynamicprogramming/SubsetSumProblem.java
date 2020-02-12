package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/2/2020 9:42 AM
 * This class demonstrates how to check if there exists a subset in an array whose sum is equal to a given sum
 */
//Reference : https://www.techiedelight.com/subset-sum-problem/
public class SubsetSumProblem {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 6};
        int sum = 13;

        SubsetSumProblem instance = new SubsetSumProblem();

        boolean subsetExists = instance.checkIfSubSetExistsRecursively(array, array.length, sum);
        System.out.println("Subset equal to given sum " + sum + " exists?  " + subsetExists + ". TC is exponential");

        subsetExists = instance.checkIfSubSetExists_DP_TopDown(array, array.length, sum, new LinkedHashMap<>());
        System.out.println("Subset equal to given sum " + sum + " exists?  " + subsetExists + ". TC is O(N*Sum)");

        subsetExists = instance.checkIfSubSetExists_DP_BottomUp(array, array.length, sum);
        System.out.println("Subset equal to given sum " + sum + " exists?  " + subsetExists + ". TC is O(N*Sum)");
    }

    public boolean checkIfSubSetExists_DP_BottomUp(int[] array, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (array[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - array[i - 1]];

            }
        }
        return dp[n][sum];
    }

    public boolean checkIfSubSetExists_DP_TopDown(int[] array, int n, int sum, Map<String, Boolean> map) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;
        String key = n + "-" + sum;
        if (!map.containsKey(key)) {
            boolean exclude = checkIfSubSetExists_DP_TopDown(array, n - 1, sum, map);
            boolean include = checkIfSubSetExists_DP_TopDown(array, n - 1, sum - array[n - 1], map);
            map.put(key, exclude || include);
        }
        return map.get(key);
    }

    public boolean checkIfSubSetExistsRecursively(int[] array, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;
        boolean exclude = checkIfSubSetExistsRecursively(array, n - 1, sum);
        boolean include = checkIfSubSetExistsRecursively(array, n - 1, sum - array[n - 1]);
        return exclude || include;
    }
}
