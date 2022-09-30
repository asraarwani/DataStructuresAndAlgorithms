package com.some_domain.www.interviewquestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 30-09-2022 05:00 pm
 * <p>
 * Reference  : https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 */
public class AnagramCheck {

    public static void main(String[] args) {

        String firstString = "gram";
        String secondString = "grma";

        AnagramCheck instance = new AnagramCheck();
        boolean isAnagram = instance.isAnagramOfOther(firstString, secondString);
        System.out.println(firstString + " is anagram of " + secondString + " : " + isAnagram);
        System.out.println("Time complexity is O(NLogN) - for Sorting");

        isAnagram = instance.isAnagramOfOtherUsingMap(firstString, secondString);
        System.out.println(firstString + " is anagram of " + secondString + " : " + isAnagram);
        System.out.println("Time complexity and space complexity is O(N) using Map");

        isAnagram = instance.isAnagramOfOtherUsingCountArray(firstString, secondString);
        System.out.println(firstString + " is anagram of " + secondString + " : " + isAnagram);
        System.out.println("Time complexity and space complexity is O(N) and O(1) respectively.");
    }

    private boolean isAnagramOfOther(String firstString, String secondString) {

        if (firstString.length() != secondString.length())
            return false;

        char[] firstStringAsArray = firstString.toCharArray();
        char[] secondStringAsArray = secondString.toCharArray();
        Arrays.sort(firstStringAsArray);
        Arrays.sort(secondStringAsArray);

        //return Arrays.equals(firstStringAsArray, secondStringAsArray);

        for (int i = 0; i < firstStringAsArray.length; i++) {
            if (firstStringAsArray[i] != secondStringAsArray[i])
                return false;
        }
        return true;
    }

    private boolean isAnagramOfOtherUsingMap(String firstString, String secondString) {
        if (firstString.length() != secondString.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();
        char[] firstStringAsArray = firstString.toCharArray();
        for (char ch : firstStringAsArray) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        char[] secondStringAsArray = secondString.toCharArray();
        for (char ch : secondStringAsArray) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
            } else {
                return false;
            }
        }
        return map.entrySet().stream().allMatch(entry -> entry.getValue() == 0);
    }

    private boolean isAnagramOfOtherUsingCountArray(String firstString, String secondString) {

        if (firstString.length() != secondString.length())
            return false;

        int[] countArray = new int[256];
        char[] firstStringAsArray = firstString.toCharArray();
        char[] secondStringAsArray = secondString.toCharArray();
        for (int i = 0; i < firstStringAsArray.length; i++) {
            countArray[firstStringAsArray[i]]++;
            countArray[secondStringAsArray[i]]--;
        }

        for (int i = 0; i < 256; i++) {
            if (countArray[i] != 0)
                return false;
        }
        return true;
    }
}
