package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 2/20/2020 11:10 AM
 * This class demonstrates how to print the length of the longest palindromic sub sequence (sub-string) of a given string (using LCS technique)
 */
//Reference : https://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
public class LongestPalindromicSubSequenceLength {

    public static void main(String[] args) {

        String string = "ABBDCACB";

        LongestPalindromicSubSequenceLength instance = new LongestPalindromicSubSequenceLength();
        int longestPalindromeLength = instance.getLongestPalindromicSubSequenceLengthRecursively(string, 0, string.length() - 1);
        System.out.println("Longest Palindromic SubSequence length using Recursive approach   " + longestPalindromeLength + ". Time complexity is O(2^N)");

        longestPalindromeLength = instance.getLongestPalindromicSubSequenceLength_DP_TopDown(string, 0, string.length() - 1, new LinkedHashMap<>());
        System.out.println("Longest Palindromic SubSequence length using DP approach          " + longestPalindromeLength + ". Time complexity is O(N^2)");

        String reversedString = new StringBuffer(string).reverse().toString();
        longestPalindromeLength = instance.getLongestPalindromicSubSequenceLength_DP_BottomUp(string, reversedString);
        System.out.println("Longest Palindromic SubSequence length using DP approach          " + longestPalindromeLength + ". Time complexity is O(N^2)");
    }

    public int getLongestPalindromicSubSequenceLengthRecursively(String string, int staringIndex, int endingIndex) {
        if (staringIndex > endingIndex)
            return 0;
        if (staringIndex == endingIndex)
            return 1;
        if (string.charAt(staringIndex) == string.charAt(endingIndex)) {
            return 2 + getLongestPalindromicSubSequenceLengthRecursively(string, staringIndex + 1, endingIndex - 1);
        } else {
            return Math.max(
                    getLongestPalindromicSubSequenceLengthRecursively(string, staringIndex, endingIndex - 1),
                    getLongestPalindromicSubSequenceLengthRecursively(string, staringIndex + 1, endingIndex)
            );
        }
    }

    public int getLongestPalindromicSubSequenceLength_DP_TopDown(String string, int startingIndex, int endingIndex, Map<String, Integer> map) {
        if (startingIndex > endingIndex)
            return 0;
        if (startingIndex == endingIndex)
            return 1;
        String key = startingIndex + "-" + endingIndex;
        if (!map.containsKey(key)) {
            int result = 0;
            if (string.charAt(startingIndex) == string.charAt(endingIndex)) {
                result = 2 + getLongestPalindromicSubSequenceLength_DP_TopDown(string, startingIndex + 1, endingIndex - 1, map);
            } else {
                result = Math.max(
                        getLongestPalindromicSubSequenceLength_DP_TopDown(string, startingIndex, endingIndex - 1, map),
                        getLongestPalindromicSubSequenceLength_DP_TopDown(string, startingIndex + 1, endingIndex, map)
                );
            }
            map.put(key, result);
        }
        return map.get(key);
    }

    public int getLongestPalindromicSubSequenceLength_DP_BottomUp(String firstString, String secondString) {
        int firstStringLength = firstString.length();
        int secondStringLenght = secondString.length();
        int[][] dpTable = new int[firstStringLength + 1][secondStringLenght + 1];
        for (int i = 1; i <= firstStringLength; i++) {
            for (int j = 1; j <= secondStringLenght; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        return dpTable[firstStringLength][secondStringLenght];
    }
}
