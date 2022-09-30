package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 30-09-2022 09:12 pm
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] array = {1, 3, 6, 8, 9, 10};
        int key = 6;
        BinarySearch instance = new BinarySearch();
        int index = instance.binarySearch(array, key);
        if (index != -1) {
            System.out.println(key + " is present in the array at index " + index);
        } else {
            System.out.println(key + " is not present in the array");
        }
        System.out.println("Time complexity is O(Log N)");
    }

    private int binarySearch(int[] array, int key) {
        int index = -1;
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < array[mid]) {
                end = mid - 1;
            } else if (key > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return index;
    }
}
