package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : asraar
 * @date : 30-09-2022 03:38 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
 */
public class FindDuplicatesFromArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 3, 9, 2, 8};
        FindDuplicatesFromArray instance = new FindDuplicatesFromArray();
        instance.findDuplicatesFromArray(array);

        System.out.println();
        instance.findDuplicatesFromArrayAlternate(array);

        System.out.println("\nTime and space complexity for above two approaches is O(N)");

        instance.findDuplicateUsingNestedLoops(array);
        System.out.println("\nTime complexity for above approach is O(N^2)");

        instance.printDuplicatesFromArrayOptimized(array);
        System.out.println("\nTime complexity for above approach is O(N)");
    }

    private void printDuplicatesFromArrayOptimized(int[] numRay) {
        for (int i = 0; i < numRay.length; i++) {
            int mod = numRay[i] % numRay.length;
            numRay[mod] = numRay[mod] + numRay.length;
        }
        for (int i = 0; i < numRay.length; i++) {
            if (numRay[i] >= numRay.length * 2) {
                System.out.print(i + " ");
            }
        }
    }

    private void findDuplicatesFromArray(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int number : array) {
            if (set.contains(number)) {
                System.out.print(number + " ");
            }
            set.add(number);
        }
    }

    private void findDuplicatesFromArrayAlternate(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : array) {
            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
            }
        }
        map.entrySet().stream().filter(e -> e.getValue() > 1).forEach(e -> {
            System.out.print(e.getKey() + " ");
        });
    }

    private void findDuplicateUsingNestedLoops(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length - 1; j++) {
                if (array[i] == array[j]) {
                    System.out.print(array[i] + " ");
                }
            }
        }
    }
}
