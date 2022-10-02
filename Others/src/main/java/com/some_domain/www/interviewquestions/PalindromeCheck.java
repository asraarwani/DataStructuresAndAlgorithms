package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 01-10-2022 08:46 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/java-program-to-check-whether-a-string-is-a-palindrome/
 */
public class PalindromeCheck {

    public static void main(String[] args) {

        PalindromeCheck instance = new PalindromeCheck();
        String string = "abba";
        boolean isPalindrome = instance.isPalindrome(string);
        if (isPalindrome) {
            System.out.println(string + " is a palindrome.");
        } else {
            System.out.println(string + " is not a palindrome.");
        }

        isPalindrome = instance.isPalindromeAlternate(string);
        if (isPalindrome) {
            System.out.println(string + " is a palindrome.");
        } else {
            System.out.println(string + " is not a palindrome.");
        }

        isPalindrome = instance.isPalindromeRecursive(string, 0, string.length() - 1);
        if (isPalindrome) {
            System.out.println(string + " is a palindrome.");
        } else {
            System.out.println(string + " is not a palindrome.");
        }
    }

    private boolean isPalindrome(String string) {
        String reverse = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reverse += string.charAt(i);
        }
        return string.equalsIgnoreCase(reverse);
    }

    private boolean isPalindromeAlternate(String string) {
        boolean isPalindrome = true;
        int start = 0;
        int end = string.length() - 1;
        while (start <= end) {
            if (string.charAt(start) != string.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return isPalindrome;
    }

    private boolean isPalindromeRecursive(String string, int start, int end) {
        if (start >= end)
            return true;
        if (string.charAt(start) != string.charAt(end))
            return false;
        return isPalindromeRecursive(string, start + 1, end - 1);
    }
}
