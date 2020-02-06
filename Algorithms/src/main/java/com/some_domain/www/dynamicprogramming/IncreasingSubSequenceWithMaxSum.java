package com.some_domain.www.dynamicprogramming;

import java.util.Arrays;

/**
 * @author : waniasra
 * @date : 2/6/2020 11:55 AM
 * This class demonstrates how to print the maximum sum of the increasing sub-sequence in a given array
 */
//Reference : https://www.techiedelight.com/increasing-subsequence-with-maximum-sum/
public class IncreasingSubSequenceWithMaxSum {

    public static void main(String[] args) {

        int[] array = {8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11};

        IncreasingSubSequenceWithMaxSum instance = new IncreasingSubSequenceWithMaxSum();

        int maxSum = instance.getIncreasingSubsequenceWithMaximumSum(array, 0, array.length, Integer.MIN_VALUE, 0);
        System.out.println("Maximum sum of increasing sub-sequence is : " + maxSum + ". Time complexity is exponential.");

        maxSum = instance.getIncreasingSubsequenceWithMaximumSum_DP_BottomUp(array);
        System.out.println("Maximum sum of increasing sub-sequence is : " + maxSum + ". Time complexity is O(N^2).");
    }

    public int getIncreasingSubsequenceWithMaximumSum_DP_BottomUp(int[] array) {
        int[] dpTable = new int[array.length];
        dpTable[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && dpTable[i] < dpTable[j]) {
                    dpTable[i] = dpTable[j];
                }
            }
            dpTable[i] = dpTable[i] + array[i];
        }
        return Arrays.stream(dpTable).max().getAsInt();
    }

    public int getIncreasingSubsequenceWithMaximumSum(int[] array, int startingIndex, int endingIndex, int previousElement, int sum) {
        if (startingIndex == endingIndex) {
            return sum;
        }

        int excludingSum = getIncreasingSubsequenceWithMaximumSum(array, startingIndex + 1, endingIndex, previousElement, sum);

        int includeSum = sum;
        if (array[startingIndex] > previousElement) {
            includeSum = getIncreasingSubsequenceWithMaximumSum(array, startingIndex + 1, endingIndex, array[startingIndex], sum + array[startingIndex]);
        }

        return Math.max(excludingSum, includeSum);
    }
}
