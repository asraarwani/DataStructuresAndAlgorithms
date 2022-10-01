package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 01-10-2022 11:14 am
 * <p>
 * Reference :  https://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {

        LongestPalindromicSubstring instance = new LongestPalindromicSubstring();
        String string = "geeksk";
        String longestPalindromicSubstring = instance.printLongestPalindromicSubstring(string);
        System.out.println("Longest palindromic substring from string " + string + " is " + longestPalindromicSubstring);
        System.out.println("Time complexity is O(N^2)");
    }

    private String printLongestPalindromicSubstring(String string) {
        if (string.length() == 0)
            return null;
        else {
            String maxPalindromicSubstring = string.substring(0, 1);
            for (int i = 0; i < string.length() - 1; i++) {
                String maxSoFar = intermediatePalindrome(string, i, i);
                if (maxSoFar.length() > maxPalindromicSubstring.length()) {
                    maxPalindromicSubstring = maxSoFar;
                }
                maxSoFar = intermediatePalindrome(string, i, i + 1);
                if (maxSoFar.length() > maxPalindromicSubstring.length()) {
                    maxPalindromicSubstring = maxSoFar;
                }
            }
            return maxPalindromicSubstring;
        }
    }

    private String intermediatePalindrome(String string, int left, int right) {
        if (left > right)
            return null;
        while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
            left--;
            right++;
        }
        return string.substring(left + 1, right);
    }
}
