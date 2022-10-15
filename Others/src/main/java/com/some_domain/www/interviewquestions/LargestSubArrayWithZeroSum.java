package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 14-10-2022 09:39 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
 */
public class LargestSubArrayWithZeroSum {

    public static void main(String[] args) {

        int array[] = {15, -2, 2, -8, 1, 7, 10, 23};
        LargestSubArrayWithZeroSum instance = new LargestSubArrayWithZeroSum();
        instance.findLongestSubArrayWithZeroSum(array);

        instance.findLongestSubArrayWithZeroSumOptimized(array);
    }

    private void findLongestSubArrayWithZeroSum(int[] array) {
        int longestSubArraySoFar = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum == 0) {
                    longestSubArraySoFar = Math.max(longestSubArraySoFar, (j - i) + 1);
                }
            }
        }
        System.out.println("Longest sub-array with 0 sum is of length : " + longestSubArraySoFar + ". Time complexity is O(N^2)");
    }

    private void findLongestSubArrayWithZeroSumOptimized(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];

            if (array[i] == 0 && maxLength == 0) {
                maxLength = 1;
            }
            if (sum == 0) {
                maxLength = i + 1;
            }

            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        System.out.println("Longest sub-array with 0 sum is of length : " + maxLength + ". Time and space complexity is O(N)");
    }
}
