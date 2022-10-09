package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 09-10-2022 03:03 pm
 */
public class SumOfArrayElementsWithoutLoop {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4, 5};
        SumOfArrayElementsWithoutLoop instance = new SumOfArrayElementsWithoutLoop();
        int sum = instance.calculateSum(array, array.length - 1);
        System.out.println("Sum : " + sum);

        sum = instance.calculateSumAlternate(array, 0);
        System.out.println("Sum : " + sum);
    }

    private int calculateSum(int[] array, int index) {
        if (index < 0)
            return 0;
        else {
            return array[index] + calculateSum(array, index - 1);
        }
    }

    private int calculateSumAlternate(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }
        return array[index] + calculateSumAlternate(array, index + 1);
    }
}
