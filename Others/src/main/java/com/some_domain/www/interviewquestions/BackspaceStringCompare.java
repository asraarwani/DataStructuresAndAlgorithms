package com.some_domain.www.interviewquestions;

import java.util.Stack;

/**
 * @author : asraar
 * @date : 23-11-2022 03:45 pm
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {

        String firstString = "ab#c";
        String secondString = "ad#c";
        boolean result = new BackspaceStringCompare().backspaceStringCompare(firstString, secondString);
        System.out.println("Result  : " + result);
    }

    //https://www.youtube.com/watch?v=96-d8ZPjHeE&list=PLi9RQVmJD2fapKJ4DnZzAn55NJfo5IM1c&index=5
    private boolean backspaceStringCompare(String firstString, String secondString) {
        Stack<Character> firstStack = new Stack<>();
        for (char ch : firstString.toCharArray()) {
            if (ch != '#') {
                firstStack.push(ch);
            } else if (!firstStack.isEmpty()) {
                firstStack.pop();
            }
        }

        Stack<Character> secondStack = new Stack<>();
        for (char ch : secondString.toCharArray()) {
            if (ch != '#') {
                secondStack.push(ch);
            } else if (!secondStack.isEmpty()) {
                secondStack.pop();
            }
        }

        while (!firstStack.isEmpty()) {
            char current = firstStack.pop();
            if (secondStack.isEmpty() || secondStack.pop() != current) {
                return false;
            }
        }

        return firstStack.isEmpty() && secondStack.isEmpty();
    }
}
