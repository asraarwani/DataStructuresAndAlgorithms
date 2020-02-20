package com.some_domain.www.dynamicprogramming;

/**
 * @author : waniasra
 * @date : 2/20/2020 11:05 AM
 * This class demonstrates how to print the longest palindromic sub sequence (sub-string) (using LCS technique)
 */
//Reference : https://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
public class LongestPalindromicSubSequence {

    public static void main(String[] args) {

        String string = "ABBDCACB";

        LongestPalindromicSubSequence instance = new LongestPalindromicSubSequence();

        instance.printLongestCommonPalindromicSubSequence(string);
    }

    /*
        The idea is to find the longest common  sub sequence between given string and its reverse
     */
    public void printLongestCommonPalindromicSubSequence(String string) {
        String reversedString = new StringBuffer(string).reverse().toString();
        int[][] dpTable = createDPTable(string, reversedString);
        String longestPalindromicSubSequence = printLongestCommonPalindromicSubSequenceHelper(string, reversedString, string.length(), reversedString.length(), dpTable);
        System.out.println("Longest Palindromic Sub Sequence " + longestPalindromicSubSequence + " . Time complexity is O(N^2)");
    }

    private String printLongestCommonPalindromicSubSequenceHelper(String firstString, String secondString, int firstStringLength, int secondStringLength, int[][] dpTable) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return new String("");
        }
        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            return printLongestCommonPalindromicSubSequenceHelper(firstString, secondString, firstStringLength - 1, secondStringLength - 1, dpTable) + firstString.charAt(firstStringLength - 1);
        } else if (dpTable[firstStringLength - 1][secondStringLength] > dpTable[firstStringLength][secondStringLength - 1]) {
            return printLongestCommonPalindromicSubSequenceHelper(firstString, secondString, firstStringLength - 1, secondStringLength, dpTable);
        } else {
            return printLongestCommonPalindromicSubSequenceHelper(firstString, secondString, firstStringLength, secondStringLength - 1, dpTable);
        }
    }

    private int[][] createDPTable(String firstString, String reversedString) {
        int firstStringLength = firstString.length();
        int secondStringLength = reversedString.length();
        int[][] dpTable = new int[firstStringLength + 1][secondStringLength + 1];
        for (int i = 1; i <= firstStringLength; i++) {
            for (int j = 1; j <= secondStringLength; j++) {
                if (firstString.charAt(i - 1) == reversedString.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        return dpTable;
    }
}
