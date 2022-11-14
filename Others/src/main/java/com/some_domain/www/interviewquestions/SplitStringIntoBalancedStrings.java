package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 14-11-2022 02:32 pm
 */
public class SplitStringIntoBalancedStrings {

    public static void main(String[] args) {

        String string = "RLRRLLRLRL";
        SplitStringIntoBalancedStrings instance = new SplitStringIntoBalancedStrings();
        int count = instance.getBalancedStringsCount(string);
        System.out.println("Number of balanced strings is : " + count);
    }

    private int getBalancedStringsCount(String string) {
        int balancedStringCount = 0;
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (character == 'L') {
                count++;
            } else if (character == 'R') {
                count--;
            }
            if (count == 0) {
                balancedStringCount++;
            }
        }
        return balancedStringCount;
    }
}
