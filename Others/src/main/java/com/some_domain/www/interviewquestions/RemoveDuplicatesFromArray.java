package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : asraar
 * @date : 02-10-2022 12:19 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/java-program-to-remove-duplicate-elements-from-the-array/
 */
public class RemoveDuplicatesFromArray {

    public static void main(String[] args) {

        RemoveDuplicatesFromArray instance = new RemoveDuplicatesFromArray();
        int[] array = {1, 2, 3, 4, 5, 5, 5, 4};
        instance.removeDuplicatesFromGivenArray(array);

        instance.removeDuplicatesFromGivenArrayUsingMap(array);
    }

    private void removeDuplicatesFromGivenArray(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int number : array) {
            set.add(number);
        }
        System.out.println(set);
    }

    private void removeDuplicatesFromGivenArrayUsingMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : array) {
            if (!map.containsKey(number)) {
                map.put(number, number);
            }
        }
        map.entrySet().stream().forEach(entry -> System.out.print(entry.getValue() + " "));
    }
}
