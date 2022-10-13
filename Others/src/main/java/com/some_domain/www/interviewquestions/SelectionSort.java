package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 02-10-2022 12:18 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/selection-sort/
 */
public class SelectionSort {

    public static void main(String[] args) {

        int array[] = new int[]{64, 25, 12, 22, 11};
        SelectionSort instance = new SelectionSort();

        System.out.println("Before sorting " + Arrays.toString(array));
        instance.selectionSort(array);
        System.out.println("After sorting " + Arrays.toString(array));
        System.out.println("Time complexity is O(N^2)");
    }

    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int smallestValueIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallestValueIndex]) {
                    smallestValueIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[smallestValueIndex];
            array[smallestValueIndex] = temp;
        }
    }
}
