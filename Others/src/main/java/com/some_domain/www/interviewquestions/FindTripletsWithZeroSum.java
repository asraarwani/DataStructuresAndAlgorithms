package com.some_domain.www.interviewquestions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 28-10-2022 08:16 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 */
public class FindTripletsWithZeroSum {

    public static void main(String[] args) {

        int array[] = {0, -1, 2, -3, 1};
        FindTripletsWithZeroSum instance = new FindTripletsWithZeroSum();
        instance.printTripletsWithZeroSum(array);
        System.out.println("Time complexity is O(N^3) and space complexity is O(1)");

        System.out.println();
        instance.printTripletsWithZeroSumUsingHashing(array);
        System.out.println("Time complexity is O(N^2) and space complexity is O(N)");

        System.out.println();
        instance.printTripletsWithZeroSumUsingSorting(array);
        System.out.println("Time complexity is O(N^2) and space complexity is O(1)");

    }

    private void printTripletsWithZeroSum(int[] array) {
        boolean isTripletPresent = false;
        for (int i = 0; i < array.length - 2; i++) {
            for (int j = i + 1; j < array.length - 1; j++) {
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i] + array[j] + array[k] == 0) {
                        System.out.println(array[i] + " " + array[j] + " " + array[k]);
                        isTripletPresent = true;
                    }
                }
            }
        }
        if (!isTripletPresent) {
            System.out.println("There is no triplet present in given array whose sum is equal to 0");
        }
    }

    private void printTripletsWithZeroSumUsingHashing(int[] array) {
        boolean isTripletPresent = false;
        for (int i = 0; i < array.length - 1; i++) {
            Set<Integer> hash = new HashSet<>();
            for (int j = i + 1; j < array.length; j++) {
                int toBePresent = -(array[i] + array[j]);
                if (hash.contains(toBePresent)) {
                    System.out.println(array[i] + " " + array[j] + " " + toBePresent);
                    isTripletPresent = true;
                } else {
                    hash.add(array[j]);
                }
            }
        }
        if (!isTripletPresent) {
            System.out.println("There is no triplet present in given array whose sum is equal to 0");
        }
    }

    private void printTripletsWithZeroSumUsingSorting(int[] array) {
        boolean isTripletPresent = false;
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            int left = i + 1;
            int right = array.length - 1;
            int x = array[i];
            while (left < right) {
                if (x + array[left] + array[right] == 0) {
                    System.out.println(array[i] + " " + array[left] + " " + array[right]);
                    left++;
                    right--;
                    isTripletPresent = true;
                } else if (x + array[left] + array[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        if (!isTripletPresent) {
            System.out.println("There is no triplet present in given array whose sum is equal to 0");
        }
    }
}
