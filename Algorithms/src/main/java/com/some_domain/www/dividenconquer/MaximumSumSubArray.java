package com.some_domain.www.dividenconquer;

/**
 * @author : waniasra
 * @date : 10/10/2019 10:02 PM
 * This class demonstrates how to find the maximum contiguous sub-array sum using divide and conquer and other efficient approach (Kadane's Algorithm)
 */

/**
 * Given an array of integers both positive and negative , we need to find the contiguous maximum sub-array sum
 * Steps:
 * 1. Divide the array into two halves
 * 2. Find the maximum of the following 3
 * a. Maximum sub-array sum for the left half
 * b. Maximum sub -array sum for the right half
 * c. Maximum sub-array crossing sum
 * a and b can be easily calculated by doing recursive calls but c can be calculated as follows:
 * 1. Find the left sum starting from the midpoint and ending at some point on the left of midpoint
 * 2. Find the right sum starting from midpoint + 1 and ending at some point on right fo the midpoint
 */
public class MaximumSumSubArray {

    public static void main(String[] args) {

        MaximumSumSubArray instance = new MaximumSumSubArray();

        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};

        int maximumSubArraySum = instance.findMaximumSumUsingDivideAndConquer(array, 0, array.length - 1);
        System.out.println("Maximum sub array sum is " + maximumSubArraySum + ". Time Complexity is NlogN");

        maximumSubArraySum = instance.findMaximumSubArraySumUsingKadaneAlgorithm(array);
        System.out.println("Maximum sub array sum is " + maximumSubArraySum + ". Time Complexity is O(N)");

        maximumSubArraySum = instance.findMaximumSubArraySumAlternate(array);
        System.out.println("Maximum sub array sum is " + maximumSubArraySum + ". Time Complexity is O(N)");

        //Printing the contiguous sub-array with the maximum sum
        instance.printMaximumSubArraySum(array);

    }

    public void printMaximumSubArraySum(int[] array) {
        int maximumSumSoFar = 0;
        int maximumSumEndingHere = 0;
        int startingIndex = -1;
        int endingIndex = -1;
        int temporaryStartingIndex = -1;
        for (int i = 0; i < array.length; i++) {
            maximumSumEndingHere = maximumSumEndingHere + array[i];
            if (maximumSumSoFar < maximumSumEndingHere) {
                maximumSumSoFar = maximumSumEndingHere;
                startingIndex = temporaryStartingIndex;
                endingIndex = i;
            }
            if (maximumSumEndingHere < 0) {
                maximumSumEndingHere = 0;
                temporaryStartingIndex = i + 1;
            }
        }
        System.out.println("Maximum sub array sum is " + maximumSumSoFar + " starting index is " + startingIndex + " ending index is " + endingIndex);
    }

    public int findMaximumSubArraySumAlternate(int[] array) {
        int maximumSumSoFar = array[0];
        int maximumSumEndingHere = array[0];
        for (int i = 1; i < array.length; i++) {
            maximumSumEndingHere = Math.max(array[i], maximumSumEndingHere + array[i]);
            maximumSumSoFar = Math.max(maximumSumSoFar, maximumSumEndingHere);
        }
        return maximumSumSoFar;
    }

    /**
     * Idea is to watch all the positive segments of the array and keep track of the segment with the maximum sum
     */
    public int findMaximumSubArraySumUsingKadaneAlgorithm(int[] array) {
        int maximumSumSoFar = 0;
        int maximumSumEndingHere = 0;
        for (int i = 0; i < array.length; i++) {
            maximumSumEndingHere = maximumSumEndingHere + array[i];
            if (maximumSumSoFar < maximumSumEndingHere) {
                maximumSumSoFar = maximumSumEndingHere;
            }
            if (maximumSumEndingHere < 0) {
                maximumSumEndingHere = 0;
            }
        }
        return maximumSumSoFar;
    }

    public int findMaximumSumUsingDivideAndConquer(int[] array, int startingIndex, int endingIndex) {
        if (startingIndex == endingIndex) {
            return array[startingIndex];
        } else {
            int middleIndex = (startingIndex + endingIndex) / 2;
            return Math.max(Math.max(findMaximumSumUsingDivideAndConquer(array, startingIndex, middleIndex), findMaximumSumUsingDivideAndConquer(array, middleIndex + 1, endingIndex
            )), findMaximumCrossingSum(array, startingIndex, middleIndex, endingIndex));
        }
    }

    private int findMaximumCrossingSum(int[] array, int startingIndex, int middleIndex, int endingIndex) {
        //Calculate the left sum starting from midpoint to startingIndex
        int sum = 0;
        int leftSum = 0;
        for (int i = middleIndex; i >= startingIndex; i--) {
            sum = sum + array[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        //Calculate the right sum starting from middleIndex + 1 to endingIndex
        sum = 0;
        int rightSum = 0;
        for (int i = middleIndex + 1; i <= endingIndex; i++) {
            sum = sum + array[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        //Combine the result and return
        return leftSum + rightSum;
    }
}
