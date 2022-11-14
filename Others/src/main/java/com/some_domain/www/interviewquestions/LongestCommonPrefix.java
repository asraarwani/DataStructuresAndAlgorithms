package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 10-11-2022 12:23 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/longest-common-prefix-using-character-by-character-matching/
 * Reference : https://www.geeksforgeeks.org/longest-common-prefix-using-divide-and-conquer-algorithm/
 * Reference : https://www.geeksforgeeks.org/longest-common-prefix-using-sorting/
 * Reference : https://www.youtube.com/watch?v=1YQmI7F9dJ0&ab_channel=KevinNaughtonJr.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {

        String strings[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        LongestCommonPrefix instance = new LongestCommonPrefix();
        String lcp = instance.findLongestCommonPrefixUsingCharacterMatching(strings);
        System.out.println("LCP using character matching is  : " + lcp);
        System.out.println("Time complexity is O(N*M) where N is the number of strings and M is the length of smallest string");

        System.out.println();
        lcp = instance.findLongestCommonPrefixUsingDivideAndConquer(strings, 0, strings.length - 1);
        System.out.println("LCP using divide and conquer is  : " + lcp);
        System.out.println("Time complexity is O(N*M) where N is the number of strings and M is the length of smallest string");


        System.out.println();
        lcp = instance.findLongestCommonPrefixUsingSorting(strings);
        System.out.println("LCP using sorting is  : " + lcp);
        System.out.println("Time complexity is O(MAX * N * log N) where N is the number of strings in the array and MAX is maximum number of characters in any string");

        System.out.println();
        lcp = instance.lcp(strings);
        System.out.println("LCP using alternate approach is  : " + lcp);
        System.out.println("Time complexity is O(N*M)");

    }

    private String findLongestCommonPrefixUsingCharacterMatching(String[] strings) {
        String result = "";
        int len = findLengthOfSmallestString(strings);
        for (int i = 0; i < len; i++) {
            char c = strings[0].charAt(i);
            for (int j = 1; j < strings.length; j++) {
                if (strings[j].charAt(i) != c) {
                    if (result == "") {
                        result = "-1";
                    }
                    return result;
                }
            }
            result += c;
        }
        if (result == "") {
            result = "-1";
        }
        return result;
    }

    private int findLengthOfSmallestString(String[] strings) {
        int minLength = strings[0].length();
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].length() < minLength) {
                minLength = strings[i].length();
            }
        }
        return minLength;
    }

    private String findLongestCommonPrefixUsingDivideAndConquer(String[] strings, int start, int end) {
        if (start == end)
            return strings[start];
        else {
            int mid = start + (end - start) / 2;
            String stringOne = findLongestCommonPrefixUsingDivideAndConquer(strings, start, mid);
            String stringTwo = findLongestCommonPrefixUsingDivideAndConquer(strings, mid + 1, end);
            return findLongestCommonPrefixUsingDivideAndConquerHelper(stringOne, stringTwo);
        }
    }

    private String findLongestCommonPrefixUsingDivideAndConquerHelper(String stringOne, String stringTwo) {
        String result = "";
        for (int i = 0, j = 0; i < stringOne.length() && j < stringTwo.length(); i++, j++) {
            if (stringOne.charAt(i) != stringTwo.charAt(j)) {
                break;
            }
            result += stringOne.charAt(i);
        }
        return result;
    }


    private String findLongestCommonPrefixUsingSorting(String[] strings) {
        int size = strings.length;
        if (size == 0)
            return "";
        if (size == 1)
            return strings[0];

        Arrays.sort(strings);

        int min = Math.min(strings[0].length(), strings[size - 1].length());
        int i = 0;
        while (i < min && strings[0].charAt(i) == strings[size - 1].charAt(i)) {
            i++;
        }
        return strings[0].substring(0, i);
    }

    private String lcp(String[] strings) {
        String lcp = "";
        if (strings == null || strings.length == 0)
            return lcp;
        int index = 0;
        for (char ch : strings[0].toCharArray()) {
            for (int i = 1; i < strings.length; i++) {
                if (index >= strings.length || ch != strings[i].charAt(index)) {
                    return lcp;
                }
            }
            lcp += ch;
            index++;
        }
        return lcp;
    }
}
