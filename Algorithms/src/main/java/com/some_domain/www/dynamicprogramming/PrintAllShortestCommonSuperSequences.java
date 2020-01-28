package com.some_domain.www.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 1/28/2020 10:52 AM
 * This class demonstrates how to print all shortest common super sequences
 */
//Reference : https://www.techiedelight.com/shortest-common-supersequence-finding-scs/
public class PrintAllShortestCommonSuperSequences {

    public static void main(String[] args) {

        String firstString = "abc";
        String secondString = "abd";

        PrintAllShortestCommonSuperSequences instance = new PrintAllShortestCommonSuperSequences();

        instance.printAllShortestCommonSuperSequences(firstString, secondString);
    }

    public void printAllShortestCommonSuperSequences(String firstString, String secondString) {
        int[][] dpTable = createDPTable(firstString, secondString);
        List<String> shortestCommonSuperSeqList = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstString.length(), secondString.length(), dpTable);
        Set<String> set = shortestCommonSuperSeqList.stream().collect(Collectors.toSet());
        set.stream().forEach(scss -> {
            System.out.println(scss);
        });
    }

    public List<String> printAllShortestCommonSuperSequencesHelper(String firstString, String secondString, int firstStringLenth, int secondStringLength, int[][] dpTable) {

        //If firstString exhausts
        if (firstStringLenth == 0) {
            List<String> list = new ArrayList<>();
            list.add(secondString.substring(0, secondStringLength));
            return list;
        }

        //If secondString exhausts
        if (secondStringLength == 0) {
            List<String> list = new ArrayList<>();
            list.add(firstString.substring(0, firstStringLenth));
            return list;
        }

        //If characters of two strings match
        if (firstString.charAt(firstStringLenth - 1) == secondString.charAt(secondStringLength - 1)) {
            List<String> list = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstStringLenth - 1, secondStringLength - 1, dpTable);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) + firstString.charAt(firstStringLenth - 1));
            }
            return list;
        }

        //If top cell value is smaller than left cell value
        if (dpTable[firstStringLenth - 1][secondStringLength] < dpTable[firstStringLenth][secondStringLength - 1]) {
            List<String> list = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstStringLenth - 1, secondStringLength, dpTable);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) + firstString.charAt(firstStringLenth - 1));
            }
            return list;
        }

        //If left cell value is smaller than top cell value
        if (dpTable[firstStringLenth][secondStringLength - 1] < dpTable[firstStringLenth - 1][secondStringLength]) {
            List<String> list = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstStringLenth, secondStringLength - 1, dpTable);
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) + secondString.charAt(secondStringLength - 1));
            }
            return list;
        }

        //If top and left cell both have equal value
        List<String> topList = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstStringLenth - 1, secondStringLength, dpTable);
        for (int i = 0; i < topList.size(); i++) {
            topList.set(i, topList.get(i) + firstString.charAt(firstStringLenth - 1));
        }

        List<String> leftList = printAllShortestCommonSuperSequencesHelper(firstString, secondString, firstStringLenth, secondStringLength - 1, dpTable);
        for (int i = 0; i < leftList.size(); i++) {
            leftList.set(i, leftList.get(i) + secondString.charAt(secondStringLength - 1));
        }

        topList.addAll(leftList);
        return topList;
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
        return dpTable;
    }

    /*private int[][] createDPTable(String firstString, String secondString) {
        int firstStringLength = firstString.length();
        int secondStringLength = secondString.length();
        int[][] dpTable = new int[firstStringLength + 1][secondStringLength + 1];

        for (int i = 0; i <= firstStringLength; i++) {
            dpTable[i][0] = i;
        }
        for (int j = 0; j <= secondStringLength; j++) {
            dpTable[0][j] = j;
        }

        for (int i = 1; i <= firstStringLength; i++) {
            for (int j = 1; j <= secondStringLength; j++) {
                if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = 1 + Math.min(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        return dpTable;
    }*/
}
