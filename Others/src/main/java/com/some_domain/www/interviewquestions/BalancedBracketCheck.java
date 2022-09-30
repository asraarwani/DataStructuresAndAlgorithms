package com.some_domain.www.interviewquestions;

import java.util.Stack;

/**
 * @author : asraar
 * @date : 30-09-2022 11:24 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 */
public class BalancedBracketCheck {

    public static void main(String[] args) {
        String expression = "([{}])";
        BalancedBracketCheck instance = new BalancedBracketCheck();
        boolean balanced = instance.checkIfBracketsAreBalanced(expression);
        if (balanced) {
            System.out.println("Balanced");
        } else {
            System.out.println("Not Balanced");
        }
        System.out.println("Time and space complexity is O(N)");
    }

    public boolean checkIfBracketsAreBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            if (character == '(' || character == '{' || character == '[') {
                stack.push(character);
                continue;
            }

            if (stack.isEmpty())
                return false;

            char openingCharacter;
            switch (character) {
                case ')':
                    openingCharacter = stack.pop();
                    if (openingCharacter == '{' || openingCharacter == '[')
                        return false;
                    break;
                case '}':
                    openingCharacter = stack.pop();
                    if (openingCharacter == '(' || openingCharacter == '[')
                        return false;
                    break;
                case ']':
                    openingCharacter = stack.pop();
                    if (openingCharacter == '(' || openingCharacter == '{')
                        return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}
