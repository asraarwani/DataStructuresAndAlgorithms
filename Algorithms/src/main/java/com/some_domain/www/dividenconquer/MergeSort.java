package com.some_domain.www.dividenconquer;

import java.util.Arrays;

/**
 * @author : waniasra
 * @date : 1/26/2020 9:19 AM
 * This class demonstrates how to sort an array using merge sort technique
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] array = new int[]{2, 4, 6, 12, 41, 89, 65, 43, 1};

        System.out.println("Array before sorting");
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));

        MergeSort instance = new MergeSort();

        instance.mergeSort(array, 0, array.length - 1);

        System.out.println("\nArray after sorting");
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));

        System.out.println("\nTime complexity is NlogN where N is the number of elements in array");
    }

    public void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] temporaryArray = new int[' '];
        for (int i = start; i <= end; i++) {
            temporaryArray[i] = array[i];
        }

        int i = start;
        int j = mid + 1;
        int k = start;
        while ((i <= mid) && (j <= end)) {
            if (temporaryArray[i] <= temporaryArray[j]) {
                array[k] = temporaryArray[i];
                i++;
                k++;
            } else {
                array[k] = temporaryArray[j];
                j++;
                k++;
            }
        }

        if (i <= mid) {
            while (i <= mid) {
                array[k] = temporaryArray[i];
                i++;
                k++;
            }
        }

        if (j <= end) {
            while (j <= end) {
                array[k] = temporaryArray[j];
                j++;
                k++;
            }
        }
    }
}
