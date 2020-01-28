package com.some_domain.www.dynamicprogramming;

import java.util.Arrays;

/**
 * @author : waniasra
 * @date : 1/27/2020 12:03 PM
 */
//Reference : https://www.techiedelight.com/shortest-common-supersequence-finding-scs/
public class PrintShortestCommonSuperSequence {

    public static void main(String[] args) {

        String firstString = "abc";
        String secondString = "abd";

        PrintShortestCommonSuperSequence instance = new PrintShortestCommonSuperSequence();

        instance.printShortestCommonSuperSequence(firstString, secondString);
    }

    public void printShortestCommonSuperSequence(String firstString, String secondString) {
        int[][] dpTable = createDPTable(firstString, secondString);
        String shortestCommonSuperSeq = printShortestCommonSuperSequenceHelper(firstString, secondString, firstString.length(), secondString.length(), dpTable);
        System.out.println("Shortest common super-sequence is [ " + shortestCommonSuperSeq + " ]. Time complexity is O(M*N) where M and N are the lengths fo the two strings.");
    }

    private String printShortestCommonSuperSequenceHelper(String firstString, String secondString, int firstStringLength, int secondStringLength, int[][] dpTable) {
        if (firstStringLength == 0)
            return secondString.substring(0, secondStringLength);
        if (secondStringLength == 0)
            return firstString.substring(0, firstStringLength);

        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            return printShortestCommonSuperSequenceHelper(firstString, secondString, firstStringLength - 1, secondStringLength - 1, dpTable) + firstString.charAt(firstStringLength - 1);
        } else {
            if (dpTable[firstStringLength - 1][secondStringLength] < dpTable[firstStringLength][secondStringLength - 1]) {
                return printShortestCommonSuperSequenceHelper(firstString, secondString, firstStringLength - 1, secondStringLength, dpTable) + firstString.charAt(firstStringLength - 1);
            } else {
                return printShortestCommonSuperSequenceHelper(firstString, secondString, firstStringLength, secondStringLength - 1, dpTable) + secondString.charAt(secondStringLength - 1);
            }
        }
    }

    private int[][] createDPTable(String firstString, String secondString) {
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
        //printTable(dpTable);
        return dpTable;
    }

    private void printTable(int[][] dpTable) {
        //System.out.println(Arrays.deepToString(dpTable).split(" "));
        String[] strings = Arrays.deepToString(dpTable).split("],");
        Arrays.stream(strings).forEach(row -> System.out.println(row));
    }
}
