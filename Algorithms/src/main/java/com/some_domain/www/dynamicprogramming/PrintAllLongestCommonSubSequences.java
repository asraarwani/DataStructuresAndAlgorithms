package com.some_domain.www.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 1/26/2020 11:04 AM
 * This class demonstrates how to print all the possible longest common sub sequences between two strings
 */
//Reference : https://www.techiedelight.com/longest-common-subsequence-finding-lcs/
public class PrintAllLongestCommonSubSequences {

    public static void main(String[] args) {

        String firstString = "ABCBDAB";
        String secondString = "BDCABA";

        PrintAllLongestCommonSubSequences instance = new PrintAllLongestCommonSubSequences();
        instance.printAllPossibleLongestCommonSubSequences(firstString, secondString);
    }

    public void printAllPossibleLongestCommonSubSequences(String firstString, String secondString) {
        int[][] dpTable = createDPTable(firstString, secondString);
        int lcsLength = dpTable[firstString.length()][secondString.length()];
        List<String> lcsList = getAllPossibleLongestCommonSubSequences(firstString, secondString, firstString.length(), secondString.length(), dpTable);
        List<String> lcs = lcsList.stream().filter(string -> string.length() == lcsLength).collect(Collectors.toList());
        System.out.println(lcs);
    }

    private List<String> getAllPossibleLongestCommonSubSequences(String firstString, String secondString, int firstStringLength, int secondStringLength, int[][] dpTable) {
        if (firstStringLength == 0 || secondStringLength == 0) {
            return new ArrayList<>(Collections.nCopies(1, ""));
        }

        //If the two characters match, we continue with the rest of the two strings and also append the matching character to the resulting strings
        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            List<String> list = getAllPossibleLongestCommonSubSequences(firstString, secondString, firstStringLength - 1, secondStringLength - 1, dpTable);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) + firstString.charAt(firstStringLength - 1));
            }
            return list;
        }

        //If top cell of current cell has greater value than left cell, we ignore the current character of the firstString and continue with the rest of the two strings
        if (dpTable[firstStringLength - 1][secondStringLength] > dpTable[firstStringLength][secondStringLength - 1]) {
            return getAllPossibleLongestCommonSubSequences(firstString, secondString, firstStringLength - 1, secondStringLength, dpTable);
        }

        //If left cell of current cell has grater value than top cell, we ignore the current character of the secondString and continue with the rest of the two strings
        if (dpTable[firstStringLength][secondStringLength - 1] > dpTable[firstStringLength - 1][secondStringLength]) {
            return getAllPossibleLongestCommonSubSequences(firstString, secondString, firstStringLength, secondStringLength - 1, dpTable);
        }

        //If top and left cell of current cell have equal values, we consider both the characters
        List<String> topList = getAllPossibleLongestCommonSubSequences(firstString, secondString, firstStringLength - 1, secondStringLength, dpTable);
        List<String> leftList = getAllPossibleLongestCommonSubSequences(firstString, secondString, firstStringLength, secondStringLength - 1, dpTable);
        topList.addAll(leftList);
        return topList;
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
