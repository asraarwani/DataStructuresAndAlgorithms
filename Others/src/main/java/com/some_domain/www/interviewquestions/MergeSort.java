package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 02-10-2022 12:18 pm
 * <p>
 * Reference : https://www.youtube.com/watch?v=aiUHB-3EOg8&ab_channel=Pepcoding
 */
public class MergeSort {

    public static void main(String[] args) {

        int firstArray[] = new int[]{10, 4, 6, 2, 3, 5, 8, 9, 7, 1};
        MergeSort instance = new MergeSort();
        int[] mergedArray = instance.mergeSort(firstArray, 0, firstArray.length - 1);
        System.out.println(Arrays.toString(mergedArray));
        System.out.println("Time complexity is N(logN), space  complexity is O(N)");
    }

    public int[] mergeSort(int[] array, int start, int end) {
        if (start == end) {
            int[] resultArray = new int[1];
            resultArray[0] = array[start];
            return resultArray;
        } else {
            int mid = start + (end - start) / 2;
            int[] firstSortedArray = mergeSort(array, start, mid);
            int[] secondSortedArray = mergeSort(array, mid + 1, end);
            int[] resultArray = merge(firstSortedArray, secondSortedArray);
            return resultArray;
        }
    }

    private int[] merge(int[] firstSortedArray, int[] secondSortedArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] resultArray = new int[firstSortedArray.length + secondSortedArray.length];
        while (i < firstSortedArray.length && j < secondSortedArray.length) {
            if (firstSortedArray[i] < secondSortedArray[j]) {
                resultArray[k] = firstSortedArray[i];
                i++;
            } else {
                resultArray[k] = secondSortedArray[j];
                j++;
            }
            k++;
        }

        while (i < firstSortedArray.length) {
            resultArray[k] = firstSortedArray[i];
            i++;
            k++;
        }

        while (j < secondSortedArray.length) {
            resultArray[k] = secondSortedArray[j];
            j++;
            k++;
        }
        return resultArray;
    }
}
