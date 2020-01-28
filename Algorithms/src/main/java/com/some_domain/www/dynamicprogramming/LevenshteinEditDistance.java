package com.some_domain.www.dynamicprogramming;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : waniasra
 * @date : 1/28/2020 9:20 PM
 * This class demonstrates Levenshtein's distance or edit distance problem
 */

/*
    Minimum number of single character operations (edits like insert, remove and replace) performed on one string to convert it to another say S1 to S2
 */

//Reference : https://www.geeksforgeeks.org/edit-distance-dp-5/
//Reference : https://www.techiedelight.com/levenshtein-distance-edit-distance-problem/
public class LevenshteinEditDistance {

    public static void main(String[] args) {

        String firstString = "Sunday";
        String secondString = "Saturday";

        LevenshteinEditDistance instance = new LevenshteinEditDistance();

        int editDistance = instance.getEditDistanceRecursively(firstString, secondString, firstString.length(), secondString.length());
        System.out.println("Levenshtein's distance or edit distance is : " + editDistance + ". Time complexity is exponential ( O(3^N))");

        editDistance = instance.getEditDistance_DP_TopDown(firstString, secondString, firstString.length(), secondString.length(), new LinkedHashMap<>());
        System.out.println("Levenshtein's distance or edit distance is : " + editDistance + ". Time complexity is O(M*N) where M and N are the lengths of the first and second string resp.");

        editDistance = instance.getEditDistance_DP_BottomUp(firstString, secondString, firstString.length(), secondString.length());
        System.out.println("Levenshtein's distance or edit distance is : " + editDistance + " .Time complexity is O(M*N) where M and N are the lenghts of the first and second string resp.");
    }

    public int getEditDistanceRecursively(String firstString, String secondString, int firstStringLength, int secondStringLength) {
        if (firstStringLength == 0)
            return secondStringLength;
        if (secondStringLength == 0)
            return firstStringLength;
        if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
            return getEditDistanceRecursively(firstString, secondString, firstStringLength - 1, secondStringLength - 1);
        } else {
            return 1 + getMinimum(
                    getEditDistanceRecursively(firstString, secondString, firstStringLength - 1, secondStringLength),
                    getEditDistanceRecursively(firstString, secondString, firstStringLength, secondStringLength - 1),
                    getEditDistanceRecursively(firstString, secondString, firstStringLength - 1, secondStringLength - 1)
            );
        }
    }

    public int getEditDistance_DP_TopDown(String firstString, String secondString, int firstStringLength, int secondStringLength, Map<String, Integer> distanceMap) {
        if (firstStringLength == 0)
            return secondStringLength;
        if (secondStringLength == 0)
            return firstStringLength;
        String key = firstStringLength + "_" + secondStringLength;
        if (!distanceMap.containsKey(key)) {
            int minimumDistance = 0;
            if (firstString.charAt(firstStringLength - 1) == secondString.charAt(secondStringLength - 1)) {
                minimumDistance = getEditDistance_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength - 1, distanceMap);
            } else {
                minimumDistance = 1 + getMinimum(
                        getEditDistance_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength, distanceMap),
                        getEditDistance_DP_TopDown(firstString, secondString, firstStringLength, secondStringLength - 1, distanceMap),
                        getEditDistance_DP_TopDown(firstString, secondString, firstStringLength - 1, secondStringLength - 1, distanceMap)
                );
            }
            distanceMap.put(key, minimumDistance);
        }
        return distanceMap.get(key);
    }

    public int getEditDistance_DP_BottomUp(String firstString, String secondString, int firstStringLength, int secondStringLength) {
        int[][] dpTable = new int[firstStringLength + 1][secondStringLength + 1];
        for (int i = 0; i <= firstStringLength; i++) {
            for (int j = 0; j <= secondStringLength; j++) {
                if (i == 0) {
                    dpTable[i][j] = j;
                } else if (j == 0) {
                    dpTable[i][j] = i;
                } else if (firstString.charAt(i - 1) == secondString.charAt(j - 1)) {
                    dpTable[i][j] = dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = 1 + getMinimum(
                            dpTable[i - 1][j],
                            dpTable[i][j - 1],
                            dpTable[i - 1][j - 1]
                    );
                }
            }
        }
        return dpTable[firstStringLength][secondStringLength];
    }

    private int getMinimum(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}
