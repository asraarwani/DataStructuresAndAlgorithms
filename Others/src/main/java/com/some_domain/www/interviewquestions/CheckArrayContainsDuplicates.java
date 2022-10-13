package com.some_domain.www.interviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 30-09-2022 08:59 pm
 */
public class CheckArrayContainsDuplicates {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 9, 2, 8};
        boolean containsDuplicate = new CheckArrayContainsDuplicates().checkIfArrayContainsDuplicate(array);
        System.out.println("Array contains duplicates : " + containsDuplicate);
    }

    private boolean checkIfArrayContainsDuplicate(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (set.contains(num))
                return true;
            set.add(num);
        }
        return false;
    }
}
