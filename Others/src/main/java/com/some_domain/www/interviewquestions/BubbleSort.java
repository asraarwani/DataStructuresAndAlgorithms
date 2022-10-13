package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 02-10-2022 12:18 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/bubble-sort/
 */
public class BubbleSort {

    public static void main(String[] args) {

        int array[] = new int[]{64, 34, 25, 12, 22, 11, 90};

        BubbleSort instance = new BubbleSort();
        System.out.println("Before sorting " + Arrays.toString(array));
        instance.bubbleSort(array);
        System.out.println("After sorting " + Arrays.toString(array));
        System.out.println("Time complexity is O(N^2)");
    }

    private void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
