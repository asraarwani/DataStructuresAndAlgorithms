package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 12-10-2022 10:05 pm
 */
public class FindMissingNumberInGivenArray {

    public static void main(String[] args) {

        int[] array = {1, 2, 4, 5};
        FindMissingNumberInGivenArray instance = new FindMissingNumberInGivenArray();

        int missingNumber = instance.findMissingNumber(array, array.length + 1);
        System.out.println("Missing number is : " + missingNumber);
    }


    private int findMissingNumber(int[] array, int n) {
        int totalSum = n * (n + 1) / 2;
        for (int num : array) {
            totalSum = totalSum - num;
        }
        return totalSum;
    }
}
