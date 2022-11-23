package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 23-11-2022 03:32 pm
 */
public class FindPeakElement {

    public static void main(String[] args) {

        int array[] = {1, 3, 20, 4, 1, 0};

        FindPeakElement instance = new FindPeakElement();
        int peak = instance.findPeakElementIndex(array);
        System.out.println("Peak element index is " + peak + " . Time complexity is O(N)");

        System.out.println();
        peak = instance.findPeakElementIndexUsingBinarySearch(array);
        System.out.println("Peak element index is " + peak + " . Time complexity is O(LogN)");

    }

    //https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
    private int findPeakElementIndex(int[] array) {
        int numOfElements = array.length;

        if (numOfElements == 1) {
            return 0;
        }

        if (array[0] >= array[1]) {
            return 0;
        }

        if (array[numOfElements - 1] >= array[numOfElements - 2]) {
            return numOfElements - 1;
        }

        for (int i = 1; i < numOfElements - 1; i++) {
            if (array[i] >= array[i - 1] && array[i] >= array[i + 1])
                return i;
        }
        return 0;
    }

    //https://www.youtube.com/watch?v=CFgUQUL7j_c&list=PLi9RQVmJD2fapKJ4DnZzAn55NJfo5IM1c&index=4
    private int findPeakElementIndexUsingBinarySearch(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
