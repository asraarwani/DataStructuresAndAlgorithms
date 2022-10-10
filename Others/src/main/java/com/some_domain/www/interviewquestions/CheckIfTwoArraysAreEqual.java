package com.some_domain.www.interviewquestions;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 10-10-2022 10:07 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/check-if-two-arrays-are-equal-or-not/
 */
public class CheckIfTwoArraysAreEqual {

    public static void main(String[] args) {

        int firstArray[] = {3, 5, 2, 5, 2};
        int secondArray[] = {2, 3, 5, 5, 2};

        CheckIfTwoArraysAreEqual instance = new CheckIfTwoArraysAreEqual();
        boolean equal = instance.areArraysEqualUsingMap(firstArray, secondArray);
        if (equal) {
            System.out.println("Two given arrays are equal");
        } else {
            System.out.println("Two given arrays are not equal");
        }
        System.out.println("Time and space complexity is O(N)");


        System.out.println();
        equal = instance.areArraysEqual(firstArray, secondArray);
        if (equal) {
            System.out.println("Two given arrays are equal");
        } else {
            System.out.println("Two given arrays are not equal");
        }
        System.out.println("Time complexity is O(N*logN)");

    }

    private boolean areArraysEqualUsingMap(int[] firstArray, int[] secondArray) {
        boolean equal = true;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : firstArray) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        for (int num : secondArray) {
            if (!map.containsKey(num))
                return false;

            if (map.get(num) == 0)
                return false;

            int count = map.get(num);
            count--;
            map.put(num, count);
        }
        return equal;
    }

    private boolean areArraysEqual(int[] firstArray, int[] secondArray) {
        if (firstArray.length != secondArray.length)
            return false;

        Arrays.sort(firstArray);
        Arrays.sort(secondArray);

        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] != secondArray[i])
                return false;
        }
        return true;
    }
}
