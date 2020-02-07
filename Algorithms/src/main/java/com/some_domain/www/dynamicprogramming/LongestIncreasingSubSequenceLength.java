package com.some_domain.www.dynamicprogramming;

import java.util.Arrays;

/**
 * @author : waniasra
 * @date : 2/5/2020 10:27 PM
 * This class demonstrates how to find the length of the longest increasing sub-sequence
 */
//Reference : https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
//Reference : https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
public class LongestIncreasingSubSequenceLength {

    public static void main(String[] args) {

        int[] array = {2, 5, 3, 7, 11, 8, 10, 13, 6};

        LongestIncreasingSubSequenceLength instance = new LongestIncreasingSubSequenceLength();

        int lisLength = instance.findLengthOfLongestIncreasingSubsequenceRecursive(array, 0, array.length, Integer.MIN_VALUE);
        System.out.println("Length of longest increasing sub-sequence is " + lisLength + ". Time complexity is exponential");

        lisLength = instance.findLengthOfLongestIncreasingSubSequenceDPBottomUpApproach(array);
        System.out.println("Length of longest increasing sub-sequence is " + lisLength + ". Time complexity is O(N^2)");

        lisLength = instance.findLengthOfLongestIncreasingSubSequenceEnhanced(array);
        System.out.println("Length of longest increasing sub-sequence is " + lisLength + ". Time complexity is O(NLogN)");
    }

    public int findLengthOfLongestIncreasingSubSequenceEnhanced(int[] array) {
        int[] tailTable = new int[array.length];
        tailTable[0] = array[0];
        int length = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < tailTable[0]) {
                tailTable[0] = array[i];
            } else if (array[i] > tailTable[length - 1]) {
                tailTable[length++] = array[i];
            } else {
                tailTable[findCeilingIndex(tailTable, -1, length - 1, array[i])] = array[i];
            }
        }
        return length;
    }

    private int findCeilingIndex(int[] tailTable, int leftIndex, int rightIndex, int key) {
        while (rightIndex - leftIndex > 1) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (key < tailTable[mid]) {
                rightIndex = mid;
            } else {
                leftIndex = mid;
            }
        }
        return rightIndex;
    }

    public int findLengthOfLongestIncreasingSubSequenceDPBottomUpApproach(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = 1;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && dp[j] > dp[i]) {
                    dp[i] = dp[j];
                }
            }
            dp[i]++;
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int findLengthOfLongestIncreasingSubsequenceRecursive(int[] array, int startingIndex, int endingIndex, int previousElement) {
        if (startingIndex == endingIndex)
            return 0;

        int exclude = findLengthOfLongestIncreasingSubsequenceRecursive(array, startingIndex + 1, endingIndex, previousElement);
        int include = 0;
        if (array[startingIndex] > previousElement) {
            include = 1 + findLengthOfLongestIncreasingSubsequenceRecursive(array, startingIndex + 1, endingIndex, array[startingIndex]);
        }
        return Math.max(exclude, include);
    }
}
