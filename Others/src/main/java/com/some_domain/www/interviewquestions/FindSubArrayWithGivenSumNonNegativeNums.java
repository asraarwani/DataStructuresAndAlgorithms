package com.some_domain.www.interviewquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : asraar
 * @date : 11-10-2022 10:56 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-subarray-with-given-sum/
 */
public class FindSubArrayWithGivenSumNonNegativeNums {

    public static void main(String[] args) {

        int array[] = {15, 2, 4, 8, 9, 5, 10, 23};
        int givenSum = 23;
        FindSubArrayWithGivenSumNonNegativeNums instance = new FindSubArrayWithGivenSumNonNegativeNums();
        instance.findSubArrayWithGivenSum(array, givenSum);
        System.out.println("Time complexity is O(N^2)");

        instance.findSubArrayWithGivenSumOptimized(array, givenSum);
        System.out.println("Time complexity is O(N)");

        List<Integer> indices = instance.findSubArrayWithGivenSumOptimizedTest(array, givenSum);
        System.out.println("Indices with + 1 -> Needed on GeeksForGeeks " + indices);
    }

    /*
    Start with an empty sub-array
    add elements to the sub-array until the sum is less than x( given sum ).
    If the sum is greater than x, remove elements from the start of the current sub-array.
     */
    private void findSubArrayWithGivenSumOptimized(int[] array, int givenSum) {
        int currentSum = array[0];
        int start = 0;
        for (int i = 1; i < array.length; i++) {

            while (currentSum > givenSum && start < i - 1) {
                currentSum = currentSum - array[start];
                start++;
            }

            if (currentSum == givenSum) {
                int endingIndex = i - 1;
                System.out.println("Given sum found from index " + start + " to " + endingIndex);
                return;
            }

            if (i < array.length) {
                currentSum = currentSum + array[i];
            }
        }
        System.out.println("No sub-array found.");
    }

    private void findSubArrayWithGivenSum(int[] array, int givenSum) {
        int currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum = array[i];
            if (currentSum == givenSum) {
                System.out.println("GIven sum found at index " + i);
                return;
            } else {
                for (int j = i + 1; j < array.length; j++) {
                    currentSum += array[j];
                    if (currentSum == givenSum) {
                        System.out.println("Given sum found from index " + i + " to " + j);
                        return;
                    }
                }
            }
        }
        System.out.println("No sub-array found.");
    }


    private List<Integer> findSubArrayWithGivenSumOptimizedTest(int[] array, int givenSum) {
        ArrayList<Integer> list = new ArrayList<>();
        int currentSum = array[0], start = 0, i;
        for (i = 1; i <= array.length; i++) {

            while (currentSum > givenSum && start < i - 1) {
                currentSum = currentSum - array[start];
                start++;
            }

            if (currentSum == givenSum) {
                int p = i;
                list.add(start + 1);
                list.add(p);
                return list;
            }

            if (i < array.length)
                currentSum = currentSum + array[i];
        }
        if (list.size() == 0)
            list.add(-1);
        return list;
    }
}
