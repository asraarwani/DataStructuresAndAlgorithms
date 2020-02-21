package com.some_domain.www.others;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : waniasra
 * @date : 2/20/2020 2:44 PM
 * This class demonstrates how to print all the palindromic partitions of a given string
 */
//Reference : https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-partition/
public class PrintPalindromicPartitionsOfString {

    public static void main(String[] args) {

        String s = "dad";

        PrintPalindromicPartitionsOfString instance = new PrintPalindromicPartitionsOfString();
        instance.getAllPalindromicPartitions(s);

        System.out.println();
        instance.getAllPalindromicPartitionsAlternate(s);
    }

    public void getAllPalindromicPartitions(String givenString) {
        int length = givenString.length();
        List<List<String>> allPartition = new ArrayList<>();
        Deque<String> currPartitions = new LinkedList<>();
        getAllPalindromicPartitionsHelper(allPartition, currPartitions, 0, length, givenString);

        List<String> palindromicPartitionList = allPartition.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        Set<String> palindromicPartitionSet = new HashSet<>(palindromicPartitionList);
        System.out.println(palindromicPartitionSet);
    }

    private void getAllPalindromicPartitionsHelper(List<List<String>> allPart, Deque<String> currPart, int start, int length, String input) {
        if (start == length) {
            allPart.add(new ArrayList<>(currPart));
            return;
        }
        for (int i = start; i < length; i++) {
            if (isPalindrome(input, start, i)) {
                currPart.addLast(input.substring(start, i + 1));
                getAllPalindromicPartitionsHelper(allPart, currPart, i + 1, length, input);
                currPart.removeLast();
            }
        }
    }

    private boolean isPalindrome(String input, int start, int end) {
        while (start < end) {
            if (input.charAt(start++) != input.charAt(end--))
                return false;
        }
        return true;
    }

    public void getAllPalindromicPartitionsAlternate(String string) {
        Set<String> allPalindromicPartitions = new HashSet<>();
        int len = string.length();
        int pointer = 0;
        while (pointer < len) {
            for (int i = pointer; i <= len; i++) {
                String consideredString = string.substring(pointer, i);
                if (!consideredString.isEmpty() && !allPalindromicPartitions.contains(consideredString) && new StringBuffer(consideredString).reverse().toString().equals(consideredString))
                    allPalindromicPartitions.add(consideredString);
            }
            pointer++;
        }
        System.out.println(allPalindromicPartitions);
    }
}
