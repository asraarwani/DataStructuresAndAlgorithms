package com.some_domain.www.dynamicprogramming;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/9/2020 9:48 AM
 * This class demonstrates how to check if a given array can be divided into tow subsets with equal sum
 */
//Reference : https://www.geeksforgeeks.org/partition-problem-dp-18/
public class PartitionProblem {

    public static void main(String[] args) {

        int array[] = {3, 1, 5, 9, 12};

        PartitionProblem instance = new PartitionProblem();

        boolean isPartitionPossible = instance.isPartitionPossible(array);
        System.out.println("Given set can be partitioned into two subsets with equal sum : " + isPartitionPossible + " . Time complexity is O(2^N)");

        isPartitionPossible = instance.isPartitionPossible_DP_TopDown(array);
        System.out.println("Given set can be partitioned into two subsets with equal sum : " + isPartitionPossible + " . Time complexity is O(N*Sum) using Dynamic Programming (Top-Down)");
    }

    public boolean isPartitionPossible_DP_TopDown(int[] array) {
        int sum = Arrays.stream(array).sum();
        if (sum % 2 != 0)
            return false;
        else
            return partitionUsingDP(array, array.length - 1, sum / 2, new LinkedHashMap<>());
    }

    private boolean partitionUsingDP(int[] array, int n, int sum, Map<String, Boolean> map) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;
        String key = n + "_" + sum;
        if (!map.containsKey(key)) {
            boolean exclude = partitionUsingDP(array, n - 1, sum, map);
            boolean include = partitionUsingDP(array, n - 1, sum - array[n], map);
            map.put(key, exclude || include);
        }
        return map.get(key);
    }

    public boolean isPartitionPossible(int[] array) {
        int sum = Arrays.stream(array).sum();
        if (sum % 2 != 0)
            return false;
        else
            return partition(array, array.length - 1, sum / 2);
    }

    private boolean partition(int[] array, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;

        boolean exclude = partition(array, n - 1, sum);
        boolean include = partition(array, n - 1, sum - array[n]);
        return exclude || include;
    }
}
