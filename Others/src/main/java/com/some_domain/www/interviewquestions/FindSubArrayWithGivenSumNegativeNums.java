package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 12-10-2022 10:44 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
 */
public class FindSubArrayWithGivenSumNegativeNums {

    public static void main(String[] args) {
        int[] array = {10, 2, -2, -20, 10};
        int sum = -10;

        FindSubArrayWithGivenSumNegativeNums instance = new FindSubArrayWithGivenSumNegativeNums();

        instance.findSubArrayWithGivenSum(array, sum);
        System.out.println("Time and space complexity is O(N^2)");


        System.out.println();
        instance.findSubArrayWithGivenSumOptimized(array, sum);
        System.out.println("Time and space complexity is O(N)");

    }

    private void findSubArrayWithGivenSumOptimized(int[] array, int givenSum) {
        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        int start = 0;
        int end = -1;
        for (int i = 0; i < array.length; i++) {
            currentSum = currentSum + array[i];

            if (currentSum - givenSum == 0) {
                start = 0;
                end = i;
                break;
            }

            if (map.containsKey(currentSum - givenSum)) {
                start = map.get(currentSum - givenSum) + 1;
                end = i;
                break;
            }

            map.put(currentSum, i);
        }
        if (end == -1) {
            System.out.println("Sub-array not found");
        } else {
            System.out.println("Given sum found from index " + start + " to " + end);
        }
    }

    private void findSubArrayWithGivenSum(int[] array, int givenSum) {
        int currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum = 0;
            for (int j = i; j < array.length; j++) {
                currentSum += array[j];
                if (currentSum == givenSum) {
                    System.out.println("Sum found between indexes " + i + " and " + j);
                    break;
                }
            }
        }
    }
}
