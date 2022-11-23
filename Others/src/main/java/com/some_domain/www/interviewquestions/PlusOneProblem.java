package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 23-11-2022 03:57 pm
 */
public class PlusOneProblem {

    public static void main(String[] args) {

        int[] number = {9, 9, 9};

        int[] result = new PlusOneProblem().plusOne(number);
        System.out.println(Arrays.toString(result));
    }

    //https://www.youtube.com/watch?v=_sls9AdBymI&list=PLi9RQVmJD2fapKJ4DnZzAn55NJfo5IM1c&index=7
    public int[] plusOne(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] < 9) {
                array[i]++;
                return array;
            }
            array[i] = 0;
        }
        int[] result = new int[array.length + 1];
        result[0] = 1;
        return result;
    }
}
