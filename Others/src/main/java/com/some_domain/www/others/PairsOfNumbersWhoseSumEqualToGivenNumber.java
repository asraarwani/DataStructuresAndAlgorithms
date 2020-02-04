package com.some_domain.www.others;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : waniasra
 * @date : 2/4/2020 11:19 AM
 * This class demonstrates how to print the pairs whose sum is equal to a given number
 */
public class PairsOfNumbersWhoseSumEqualToGivenNumber {

    public static void main(String[] args) {

        int[] array = {2, 4, 1, 3, 5, 7, 8, 9};
        int givenNumber = 12;

        PairsOfNumbersWhoseSumEqualToGivenNumber instance = new PairsOfNumbersWhoseSumEqualToGivenNumber();
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
        int endingIndex = array.length - 1;
        Arrays.sort(array);                    // NlogN - since it uses dual-pivot quick-sort
        while (startingIndex < endingIndex) {
            if (array[startingIndex] + array[endingIndex] == givenNumber) {
                System.out.printf("%d %d %n", array[startingIndex], array[endingIndex]);
                startingIndex++;
                endingIndex--;
            } else if (array[startingIndex] + array[endingIndex] > givenNumber) {
                endingIndex--;
            } else {
                startingIndex++;
            }
        }
    }

    public void printPairsOfNumberEqualToGivenSumImproved(int[] array, int givenNumber) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            int remaining = givenNumber - array[i];
            if (!set.contains(remaining)) {
                set.add(array[i]);
            } else {
                System.out.printf(" %d %d %n", array[i], givenNumber - array[i]);
            }
        }
    }

    public void printPairsOfNumberEqualToGivenSum(int[] array, int givenNumber) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == givenNumber) {
                    System.out.printf("%d %d %n", array[i], array[j]);
                }
            }
        }
    }
}
