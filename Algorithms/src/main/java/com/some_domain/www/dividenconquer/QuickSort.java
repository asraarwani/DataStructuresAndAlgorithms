package com.some_domain.www.dividenconquer;

import java.util.Arrays;

/**
 * @author : waniasra
 * @date : 1/26/2020 9:49 PM
 * This class demonstrates quick sort algorithm
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] array = new int[]{2, 4, 6, 12, 41, 89, 65, 43, 1};

        System.out.println("Before sorting");
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));

        System.out.println();

        new QuickSort().quickSort(array, 0, array.length - 1);
        System.out.println("After sorting");
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));

        System.out.println();
        System.out.printf("Time complexity is O(NLogN) in average case, and will be O(N^2) in worst case if we pick a wrong pivot.");
        System.out.println("We say every element goes through logN swaps and if there are N elements, it is going to be O(NLogN) in average case.");
    }

    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotElement = array[(start + end) / 2];
            int partitioningIndex = getPartitioningIndex(array, start, end, pivotElement);
            quickSort(array, start, partitioningIndex);
            quickSort(array, partitioningIndex + 1, end);
        }
    }

    private int getPartitioningIndex(int[] array, int start, int end, int pivot) {
        while (start < end) {

            while (array[start] < pivot) {
                start++;
            }

            while (array[end] > pivot) {
                end--;
            }

            if (start <= end) {
                int temporary = array[start];
                array[start] = array[end];
                array[end] = temporary;
            }
        }
        return start;
    }
}
