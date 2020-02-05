package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/5/2020 2:43 PM
 * This class demonstrates KnapsackProblem
 */
//Reference : https://www.techiedelight.com/0-1-knapsack-problem/
public class KnapsackProblem {

    public static void main(String[] args) {

       /* int values[] = new int[]{500, 1800, 1500};
        int weights[] = new int[]{130, 280, 120};
        int knapsackCapacity = 300;*/

        int values[] = {20, 5, 10, 40, 15, 25};
        int weights[] = {1, 2, 3, 8, 7, 4};
        int knapsackCapacity = 10;

      /*  int values[] = {20, 5, 15, 25};
        int weights[] = {1, 2, 3, 4};
        int knapsackCapacity = 5;*/


        KnapsackProblem instance = new KnapsackProblem();

        int knapsackValue = instance.getKnapsackValueRecursive(values, weights, values.length - 1, knapsackCapacity);
        System.out.println("Knapsack value is " + knapsackValue + ". Time complexity is O(2^N)");

        knapsackValue = instance.getKnapsackValue_DP_TopDown(values, weights, values.length - 1, knapsackCapacity, new LinkedHashMap<>());
        System.out.println("Knapsack value is " + knapsackValue + ". Time complexity is O(N*KnapsackCapacity)");

        knapsackValue = instance.getKnapsackValue_DP_BottomUp(values, weights, knapsackCapacity);
        System.out.println("Knapsack value is " + knapsackValue + ". Time complexity is O(N*KnapsackCapacity)");
    }

    public int getKnapsackValue_DP_BottomUp(int[] values, int[] weights, int knapsackCapacity) {
        int[][] dpTable = new int[values.length + 1][knapsackCapacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 0; j <= knapsackCapacity; j++) {
                //Don't include ith element if j-weights[i-1] is negative
                if (weights[i - 1] > j) {
                    dpTable[i][j] = dpTable[i - 1][j];
                } else {
                    //Find maximum value by excluding or including the i'th item
                    dpTable[i][j] = Integer.max(dpTable[i - 1][j],
                            dpTable[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dpTable[values.length][knapsackCapacity];
    }

    public int getKnapsackValue_DP_TopDown(int[] values, int[] weights, int n, int knapsackCapacity, Map<String, Integer> map) {
        if (knapsackCapacity == 0)
            return 0;
        if (knapsackCapacity < 0)
            return Integer.MIN_VALUE;
        if (n < 0)
            return 0;
        String key = n + "-" + knapsackCapacity;
        if (!map.containsKey(key)) {
            int include = values[n] + getKnapsackValue_DP_TopDown(values, weights, n - 1, knapsackCapacity - weights[n], map);
            int exclude = getKnapsackValue_DP_TopDown(values, weights, n - 1, knapsackCapacity, map);
            map.put(key, Math.max(include, exclude));
        }
        return map.get(key);
    }

    public int getKnapsackValueRecursive(int[] values, int[] weights, int n, int knapsackCapacity) {
        if (knapsackCapacity == 0)
            return 0;
        if (knapsackCapacity < 0)
            return Integer.MIN_VALUE;
        if (n < 0)
            return 0;

        int include = values[n] + getKnapsackValueRecursive(values, weights, n - 1, knapsackCapacity - weights[n]);
        int exclude = getKnapsackValueRecursive(values, weights, n - 1, knapsackCapacity);
        return Math.max(include, exclude);
    }
}
