package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 1/27/2020 11:09 AM
 * This class demonstrates how to calculate the length of longest common super sequence
 */

/*
    Shortest common super-sequence of two sequences X and Y is the shortest sequence which has X and Y as subsequences
 */

//Reference : https://www.geeksforgeeks.org/shortest-common-supersequence/
//Reference : https://www.techiedelight.com/shortest-common-supersequence-introduction-scs-length/
public class ShortestCommonSuperSequenceLength {

    public static void main(String[] args) {

        ShortestCommonSuperSequenceLength instance = new ShortestCommonSuperSequenceLength();

        String firstString = "abc";
        String secondString = "abd";

        //Shortest common super sequence : abcd

        int shortestCommonSuperSequenceLength = instance.getShortestCommonSuperSequenceLength_Recursive(firstString, secondString, firstString.length(), secondString.length());
        System.out.println("Shortest common super sequence length is           : " + shortestCommonSuperSequenceLength + " . Time complexity is O(2^min(M,N))");

        shortestCommonSuperSequenceLength = instance.getShortestCommonSuperSequenceLength(firstString, secondString);
        System.out.println("Shortest common super sequence length using LCS is : " + shortestCommonSuperSequenceLength + " . Time complexity is O(M*N)");

        shortestCommonSuperSequenceLength = instance.getShortestCommonSuperSequenceLength_DP_BottomUp(firstString, secondString);
        System.out.println("Shortest common super sequence length is           : " + shortestCommonSuperSequenceLength + " . Time complexity is O(M*N)");
        shortestCommonSuperSequenceLength = instance.getShortestCommonSuperSequenceLength_DP_TopDown(firstString, secondString, firstString.length(), secondString.length(), new LinkedHashMap<>());
        System.out.println("Shortest common super sequence length is           : " + shortestCommonSuperSequenceLength + " . Time complexity is O(M*N)");
    }

    public int getShortestCommonSuperSequenceLength_Recursive(String firstString, String secondString, int firstStringLength, int secondStringLength) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return firstStringLength + secondStringLength;
        }
        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            return 1 + getShortestCommonSuperSequenceLength_Recursive(firstString, secondString, firstStringLength - 1, secondStringLength - 1);
        } else {
            return 1 + Math.min(
                    getShortestCommonSuperSequenceLength_Recursive(firstString, secondString, firstStringLength - 1, secondStringLength),
                    getShortestCommonSuperSequenceLength_Recursive(firstString, secondString, firstStringLength, secondStringLength - 1)
            );
        }
    }

    public int getShortestCommonSuperSequenceLength(String firstString, String secondString) {
        int firstStringLength = firstString.length();
        int secondStringLength = secondString.length();
        int[][] dpTable = new int[firstStringLength + 1][secondStringLength + 1];
        for (int i = 1; i <= firstStringLength; i++) {
            for (int j = 1; j <= secondStringLength; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        int lcsLength = dpTable[firstStringLength][secondStringLength];
        //Length of the shortest super sequence  = ( Sum of lengths of two given strings - Length of LCS of two given strings )
        return (firstStringLength + secondStringLength) - lcsLength;
    }

    public int getShortestCommonSuperSequenceLength_DP_BottomUp(String firstString, String secondString) {
        int firstStringLength = firstString.length();
        int secondStringLength = secondString.length();
        int[][] dpTable = new int[firstStringLength + 1][secondStringLength + 1];
        for (int i = 0; i <= firstStringLength; i++) {
            for (int j = 0; j <= secondStringLength; j++) {
                if (i == 0) {
                    dpTable[i][j] = j;
                } else if (j == 0) {
                    dpTable[i][j] = i;
                } else if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = 1 + Math.min(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        return dpTable[firstStringLength][secondStringLength];
    }

    public int getShortestCommonSuperSequenceLength_DP_TopDown(String firstString, String secondString, int firstStringLength, int secondStringLength, Map<String, Integer> dpMap) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return firstStringLength + secondStringLength;
        }
        String key = firstStringLength + " " + secondStringLength;
        if (!dpMap.containsKey(key)) {
            int shortestCommonSuperSequenceLen = 0;
            if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
                shortestCommonSuperSequenceLen = 1 + getShortestCommonSuperSequenceLength_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength - 1, dpMap);
            } else {
                shortestCommonSuperSequenceLen = 1 + Math.min(
                        getShortestCommonSuperSequenceLength_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength, dpMap),
                        getShortestCommonSuperSequenceLength_DP_TopDown(firstString, secondString, firstStringLength, secondStringLength - 1, dpMap)
                );
            }
            dpMap.put(key, shortestCommonSuperSequenceLen);
        }
        return dpMap.get(key);
    }
}
