package com.some_domain.www.interviewquestions;


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : asraar
 * @date : 13-10-2022 11:50 am
 * <p>
 * Reference  : https://www.geeksforgeeks.org/merge-two-sorted-arrays/
 */
public class MergeTwoSortedArrays {

    public static void main(String[] args) {

        int firstArray[] = new int[]{1, 3, 5, 7, 9}, secondArray[] = new int[]{2, 4, 6, 8, 10};
        MergeTwoSortedArrays instance = new MergeTwoSortedArrays();
        instance.mergeTwoSortedArraysUsingTreeMap(firstArray, secondArray);
        System.out.println("\nTime complexity is O(NlogN + MlogM), space complexity is O(N)");

        instance.mergeTwoSortedArraysUsingSortApproach(firstArray, secondArray);
        System.out.println("Time complexity is ((M+N)log(M+N))");

        int[] resultArray = instance.mergeTwoSortedArrays(firstArray, secondArray);
        System.out.println(Arrays.toString(resultArray));
        System.out.println("Time and space complexity is O(N+M)");
    }

    private int[] mergeTwoSortedArrays(int[] firstArray, int[] secondArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] resultArray = new int[firstArray.length + secondArray.length];
        while (i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] < secondArray[j]) {
                resultArray[k] = firstArray[i];
                i++;
            } else {
                resultArray[k] = secondArray[j];
                j++;
            }
            k++;
        }

        while (i < firstArray.length) {
            resultArray[k] = firstArray[i];
            i++;
            k++;
        }

        while (j < secondArray.length) {
            resultArray[k] = secondArray[j];
            j++;
            k++;
        }
        return resultArray;
    }

    private void mergeTwoSortedArraysUsingTreeMap(int[] firstArray, int[] secondArray) {
        Map<Integer, Boolean> map = new TreeMap();
        for (int num : firstArray) {
            map.put(num, true);
        }

        for (int num : secondArray) {
            map.put(num, true);
        }

        map.entrySet().stream().forEach(entry -> System.out.print(entry.getKey() + " "));
    }

    private void mergeTwoSortedArraysUsingSortApproach(int[] firstArray, int[] secondArray) {
        int[] resultArray = new int[firstArray.length + secondArray.length];
        int i = 0;
        for (int num : firstArray) {
            resultArray[i] = num;
            i++;
        }

        for (int num : secondArray) {
            resultArray[i] = num;
            i++;
        }

        Arrays.sort(resultArray);
        System.out.println(Arrays.toString(resultArray));
    }
}
