package com.some_domain.www.interviewquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 17-10-2022 09:38 pm
 */
public class IsArraySubsetOfArray {

    public static void main(String[] args) {

        long[] mainArray = {1, 2, 3, 4, 5, 1, 1, 1};
        long[] secondArray = {1, 2, 3, 1};

        String result = new IsArraySubsetOfArray().isSubset(mainArray, secondArray);
        System.out.println(result);
    }

    private String isSubset(long firstArray[], long secondArray[]) {
        Map<Long, Integer> map = new HashMap<>();
        for (Long num : firstArray) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        Map<Long, Integer> secondMap = new HashMap<>();
        for (Long num : secondArray) {
            if (secondMap.containsKey(num)) {
                secondMap.put(num, secondMap.get(num) + 1);
            } else {
                secondMap.put(num, 1);
            }
        }

        for (long num : secondArray) {
            if (!map.containsKey(num)) {
                return "No";
            } else if (map.get(num) < secondMap.get(num)) {
                return "No";
            }
        }
        return "Yes";
    }
}
