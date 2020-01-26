package com.some_domain.www.dynamicprogramming;

/**
 * @author : waniasra
 * @date : 1/26/2020 10:40 AM
 * This class demonstrates how to print the longest common sub sequence between two strings
 */
//Reference : https://www.techiedelight.com/longest-common-subsequence-finding-lcs/
//Reference : https://www.tutorialcup.com/string/print-longest-common-subsequence.htm
public class PrintLongestCommonSubSequence {

    public static void main(String[] args) {

        String firstString = "ABCBDAB";
        String secondString = "BDCABA";

        PrintLongestCommonSubSequence instance = new PrintLongestCommonSubSequence();

        instance.printLongestCommonSubSequence(firstString, secondString);

        instance.printLongestCommonSubSequenceAlternate(firstString, secondString);
    }

    public void printLongestCommonSubSequenceAlternate(String firstString, String secondString) {
        int firstStringLength = firstString.length();
        int secondStringLength = secondString.length();
        int[][] dpTable = createDPTable(firstString, secondString);
        int index = dpTable[firstStringLength][secondStringLength];
        char[] longestCommonSubSequence = new char[index + 1];
        int i = firstStringLength;
        int j = secondStringLength;
        while (i > 0 && j > 0) {
            if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                longestCommonSubSequence[index] = firstString.charAt(i - 1);
                index--;
                i--;
                j--;
            } else if (dpTable[i - 1][j] > dpTable[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println("Longest common sub-sequence is :" + new String(longestCommonSubSequence) + ". Time complexity is O(M*N) where M and N are the number of characters in the two strings");
    }

    public void printLongestCommonSubSequence(String firstString, String secondString) {
        int[][] dpTable = createDPTable(firstString, secondString);
        String longestCommonSubSequence = getLongestCommonSubSequence(firstString, secondString, firstString.length(), secondString.length(), dpTable);
        System.out.println("Longest common sub-sequence is : " + longestCommonSubSequence + " .Time complexity is O(M*N) where M and N are the number of characters in the two strings");
    }

    private String getLongestCommonSubSequence(String firstString, String secondString, int firstStringLen, int secondStringLen, int[][] dpTable) {
        if (firstStringLen == 0 || secondStringLen == 0)
            return new String("");
        if (firstString.charAt(firstStringLen - 1) == secondString.charAt(secondStringLen - 1)) {
            return getLongestCommonSubSequence(firstString, secondString, firstStringLen - 1, secondStringLen - 1, dpTable) + firstString.charAt(firstStringLen - 1);
        } else if (dpTable[firstStringLen - 1][secondStringLen] > dpTable[firstStringLen][secondStringLen - 1]) {
            return getLongestCommonSubSequence(firstString, secondString, firstStringLen - 1, secondStringLen, dpTable);
        } else {
            return getLongestCommonSubSequence(firstString, secondString, firstStringLen, secondStringLen - 1, dpTable);
        }
    }

    private int[][] createDPTable(String firstString, String secondString) {
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
        return dpTable;
    }
}
