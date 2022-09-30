package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 30-09-2022 09:12 pm
 */
public class LinearSearch {

    public static void main(String[] args) {

        LinearSearch instance = new LinearSearch();
        int[] array = {1, 20, 5, 8, 6, 10, 7};
        int key = 10;
        boolean isPresent = instance.linearSearch(array, key);
        if (isPresent) {
            System.out.println(key + " is present in the given array");
        } else {
            System.out.println(key + " is NOT present in the given array");
        }
        System.out.println("Time complexity is O(N)");
    }

    private boolean linearSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return true;
            }
        }
        return false;
    }
}
