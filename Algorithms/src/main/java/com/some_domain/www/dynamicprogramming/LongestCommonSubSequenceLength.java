package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 1/26/2020 9:56 AM
 * This class demonstrates how to get the length of longest common subsequence between two strings
 */
//Reference : https://www.techiedelight.com/longest-common-subsequence/
public class LongestCommonSubSequenceLength {

    public static void main(String[] args) {

        LongestCommonSubSequenceLength instance = new LongestCommonSubSequenceLength();

        String firstString = "ABCBDAB";
        String secondString = "BDCABA";

        int lcsLength = instance.getLongestCommonSubSequenceLength(firstString, secondString, firstString.length(), secondString.length());
        System.out.println("Length of LCS is " + lcsLength + " . Time complexity is O(2^M+N) where m and n are the lengths of first and second string resp. (Recursive)");

        lcsLength = instance.getLongestCommonSubSequenceLength_DP_TopDown(firstString, secondString, firstString.length(), secondString.length(), new LinkedHashMap<>());
        System.out.println("Length of LCS is " + lcsLength + " . Time complexity is O(M*N) where m and n are the lengths of first and second string resp. (TopDown)");

        lcsLength = instance.getLongestCommonSubSequenceLength_DP_TopDown_Alternate(firstString, secondString, firstString.length(), secondString.length(), new int[firstString.length() + 1][secondString.length() + 1]);
        System.out.println("Length of LCS is " + lcsLength + " . Time complexity is O(M*N) where m and n are the lengths of first and second string resp. (TopDown Alternate)");

        lcsLength = instance.getLongestCommonSubSequenceLength_DP_BottomUp(firstString, secondString, firstString.length(), secondString.length());
        System.out.println("Length of LCS is " + lcsLength + " . Time complexity is O(M*N) where m and n are the lengths of first and second string resp. (BottomUp)");
    }

    public int getLongestCommonSubSequenceLength_DP_BottomUp(String firstString, String secondString, int firstStringLength, int secondStringLength) {
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
        return dpTable[firstStringLength][secondStringLength];
    }

    public int getLongestCommonSubSequenceLength_DP_TopDown_Alternate(String firstString, String secondString, int firstStringLength, int secondStringLength, int[][] dpTable) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return 0;
        }
        if (dpTable[firstStringLength][secondStringLength] == 0) {
            int lcsLength = 0;
            if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
                lcsLength = 1 + getLongestCommonSubSequenceLength_DP_TopDown_Alternate(firstString, secondString, firstStringLength - 1, secondStringLength - 1, dpTable);
            } else {
                lcsLength = Math.max(
                        getLongestCommonSubSequenceLength_DP_TopDown_Alternate(firstString, secondString, firstStringLength - 1, secondStringLength, dpTable),
                        getLongestCommonSubSequenceLength_DP_TopDown_Alternate(firstString, secondString, firstStringLength, secondStringLength - 1, dpTable)
                );
            }
            dpTable[firstStringLength][secondStringLength] = lcsLength;
        }
        return dpTable[firstStringLength][secondStringLength];
    }

    public int getLongestCommonSubSequenceLength_DP_TopDown(String firstString, String secondString, int firstStringLength, int secondStringLength, Map<String, Integer> map) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return 0;
        }

        String key = firstStringLength + "_" + secondStringLength;
        if (!map.containsKey(key)) {
            int lcsLength = 0;
            if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
                lcsLength = 1 + getLongestCommonSubSequenceLength_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength - 1, map);
            } else {
                lcsLength = Math.max(
                        getLongestCommonSubSequenceLength_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength, map),
                        getLongestCommonSubSequenceLength_DP_TopDown(firstString, secondString, firstStringLength, secondStringLength - 1, map)
                );
            }
            map.put(key, lcsLength);
        }
        return map.get(key);
    }

    public int getLongestCommonSubSequenceLength(String firstString, String secondString, int firstStringLength, int secondStringLength) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return 0;
        }
        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            return 1 + getLongestCommonSubSequenceLength(firstString, secondString, firstStringLength - 1, secondStringLength - 1);
        } else {
            return Math.max(
                    getLongestCommonSubSequenceLength(firstString, secondString, firstStringLength - 1, secondStringLength),
                    getLongestCommonSubSequenceLength(firstString, secondString, firstStringLength, secondStringLength - 1)
            );
        }
    }
}
