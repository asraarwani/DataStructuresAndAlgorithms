package com.some_domain.www.others;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 2/4/2020 11:19 AM
 * This class demonstrates how to print the pairs whose absolute difference is equal to a given number
 */
//Reference : https://www.geeksforgeeks.org/count-pairs-difference-equal-k/
//Reference : https://www.techiedelight.com/find-pairs-with-given-difference-array/
public class PairsOfNumbersWhoseDiffEqualToGivenNumber {

    public static void main(String[] args) {

        int[] array = {2, 14, 11, 3, 15, 7, 18, 19};
        int givenNumber = 12;

        PairsOfNumbersWhoseDiffEqualToGivenNumber instance = new PairsOfNumbersWhoseDiffEqualToGivenNumber();
        instance.printPairsOfNumberEqualToGivenSum(array, givenNumber);
        System.out.println("Time complexity is O(N^2)");
        System.out.println();

        instance.printPairsOfNumberEqualToGivenSumImproved(array, givenNumber);
        System.out.println("Time and space complexity is O(N)");
        System.out.println();

        instance.printPairsOfNumberEqualToGivenSumEnhanced(array, givenNumber);
        System.out.println("Time complexity is O(NLogN) and space complexity is O(1)");
        System.out.println();
    }

    public void printPairsOfNumberEqualToGivenSumEnhanced(int[] array, int givenNumber) {
        int startingIndex = 0;
        int endingIndex = 0;
        Arrays.sort(array); // NlogN - since it uses dual-pivot quick-sort
        while (endingIndex < array.length) {
            if (array[endingIndex] - array[startingIndex] == givenNumber) {
                System.out.printf("%d %d %n", array[startingIndex], array[endingIndex]);
                startingIndex++;
                endingIndex++;
            } else if (array[endingIndex] - array[startingIndex] > givenNumber) {
                startingIndex++;
            } else {
                endingIndex++;
            }
        }
    }

    public void printPairsOfNumberEqualToGivenSumImproved(int[] array, int givenNumber) {
        Set<Integer> set = Arrays.stream(array).boxed().collect(Collectors.toSet());
        for (int i = 0; i < array.length; i++) {
            if (set.contains(array[i] - givenNumber)) {
                System.out.printf(" %d %d %n", array[i], array[i] - givenNumber);
                set.remove(array[i]);
            }
            if (set.contains(array[i] + givenNumber)) {
                System.out.printf(" %d %d %n", array[i], array[i] + givenNumber);
                set.remove(array[i]);
            }
        }
    }

    public void printPairsOfNumberEqualToGivenSum(int[] array, int givenNumber) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (Math.abs(array[i] - array[j]) == givenNumber) {
                    System.out.printf("%d %d %n", array[i], array[j]);
                }
            }
        }
    }
}
