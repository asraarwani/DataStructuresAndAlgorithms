package com.some_domain.www.others;

/**
 * @author : waniasra
 * @date : 2/4/2020 12:45 PM
 * This class demonstrates how to count number of ways a number's digits can be arranged in such a way such that:
 * digit is divisible by its index
 * OR
 * index is divisible by digit
 */
//Fivetran Hackerrank interview Feb 2020
public class BeautifulNumbersArrangementsCount {

    public static void main(String[] args) {
        int n = 5;
        int count = arrangements(n);
        System.out.println(count);
    }

    static int arrangements(int n) {
        Counter counter = new Counter();
        String string = prepareString(n);
        arrangementsHelper(string, "", counter);
        return counter.count;
    }

    private static String prepareString(int n) {
        String string = "";
        for (int i = 1; i <= n; i++) {
            string = string + String.valueOf(i);
        }
        return string;
    }

    private static void arrangementsHelper(String firstString, String secondString, Counter counter) {

        if (firstString.length() == 0) {
            if (isBeautifulArrangement(secondString)) {
                System.out.println(secondString);
                counter.count = counter.count + 1;
            }
            return;
        }

        for (int i = 0; i < firstString.length(); i++) {
            char ch = firstString.charAt(i);
            String rest = firstString.substring(0, i) + firstString.substring(i + 1);
            arrangementsHelper(rest, secondString + ch, counter);
        }
    }

    private static boolean isBeautifulArrangement(String arrangement) {
        boolean isBeautiful = false;
        for (int i = 1; i <= arrangement.length(); i++) {
            int digit = Integer.parseInt(Character.toString(arrangement.charAt(i - 1)));
            if (digit % i == 0 || i % digit == 0) {
                isBeautiful = true;
            } else {
                return false;
            }
        }
        return isBeautiful;
    }

    private static class Counter {
        private int count;
    }
}
