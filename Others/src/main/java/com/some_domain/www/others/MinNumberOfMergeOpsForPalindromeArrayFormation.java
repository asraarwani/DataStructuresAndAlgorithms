package com.some_domain.www.others;

/**
 * @author : waniasra
 * @date : 10/18/2019 1:34 PM
 * This class demonstrates minimum number of merge operations required for making an array palindromic
 */
//Reference : https://www.tutorialcup.com/array/minimum-number-merge-operations-array-palindrome.htm
public class MinNumberOfMergeOpsForPalindromeArrayFormation {

    public static void main(String[] args) {

        MinNumberOfMergeOpsForPalindromeArrayFormation instance = new MinNumberOfMergeOpsForPalindromeArrayFormation();

        int[] array = {1, 3, 8, 6, 1};

        int minimumNumberOfMergeOperations = instance.findMinimumNumberOfMergeOperationRequiredForPalindromeFormation(array);
        System.out.println("Minimum number of merge operations required for making this array a palindrome : " + minimumNumberOfMergeOperations);
        System.out.println("\nTime complexity is O(N)");

    }

    public int findMinimumNumberOfMergeOperationRequiredForPalindromeFormation(int[] array) {
        int startingIndex = 0;
        int endingIndex = array.length - 1;
        int mergeOperationCount = 0;
        while (startingIndex <= endingIndex) {
            //If elements at both the indices (startingIndex and endingIndex) are same, we just move the indices accordingly (Nothing needs to be done)
            if (array[startingIndex] == array[endingIndex]) {
                startingIndex++;
                endingIndex--;
            } else if (array[startingIndex] > array[endingIndex]) { //If the element at current startingIndex is greater, we perform merge operation at endingIndex + 1 using endingIndex and endingIndex + 1 and decrement endingIndex and increment merge count
                array[endingIndex - 1] = array[endingIndex - 1] + array[endingIndex];
                endingIndex--;
                mergeOperationCount++;
            } else {  //If the element at current startingIndex is smaller, we perform merge operation at startingIndex+1 using startingIndex and startingIndex + 1 and increment startingIndex and merge count
                array[startingIndex + 1] = array[startingIndex + 1] + array[startingIndex];
                startingIndex++;
                mergeOperationCount++;
            }
        }
        return mergeOperationCount;
    }
}
