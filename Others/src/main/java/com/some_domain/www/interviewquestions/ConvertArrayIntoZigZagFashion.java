package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 11-10-2022 09:45 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/
 */
public class ConvertArrayIntoZigZagFashion {

    public static void main(String[] args) {

        int[] array = new int[]{4, 3, 7, 8, 6, 2, 1};
        ConvertArrayIntoZigZagFashion instance = new ConvertArrayIntoZigZagFashion();
        instance.convertArrayIntoZigZagFashion(array);
        System.out.println(Arrays.toString(array) + " Time complexity is O(NlogN)");

        array = new int[]{4, 3, 7, 8, 6, 2, 1};
        instance.convertArrayIntoZigZagFashionOptimized(array);
        System.out.println(Arrays.toString(array) + " Time complexity is O(N)");
    }

    private void convertArrayIntoZigZagFashionOptimized(int[] array) {
        boolean flag = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (flag) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
                flag = false;
            } else {
                if (array[i] < array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
                flag = true;
            }
        }
    }

    private void convertArrayIntoZigZagFashion(int[] array) {
        Arrays.sort(array);
        for (int i = 1; i < array.length; i = i + 2) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }
}
