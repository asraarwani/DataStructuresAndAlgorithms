package com.some_domain.www.interviewquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : asraar
 * @date : 01-10-2022 08:58 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * https://www.youtube.com/watch?v=39SKIuA-ieY&ab_channel=Pepcoding
 */
public class PermutationsOfString {

    public static void main(String[] args) {

        PermutationsOfString instance = new PermutationsOfString();
        String string = "ABA";
        instance.printPermutations(string, "");
        System.out.println("Time complexity is O(N*N!)");

        System.out.println("Using iterative approach");
        instance.printPermutationsIterative(string);

        System.out.println("Using iterative approach without duplicates");
        instance.printPermutationsIterativeWithoutDuplicates(string);
    }

    private void printPermutationsIterative(String string) {
        int factorial = getFactorial(string.length());
        for (int i = 0; i < factorial; i++) {
            StringBuilder stringBuilder = new StringBuilder(string);
            int temp = i;
            for (int div = string.length(); div >= 1; div--) {
                int quotient = temp / div;
                int remainder = temp % div;
                System.out.print(stringBuilder.charAt(remainder));
                stringBuilder.deleteCharAt(remainder);
                temp = quotient;
            }
            System.out.println();
        }
    }

    private void printPermutationsIterativeWithoutDuplicates(String string) {
        int factorial = getFactorial(string.length());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < factorial; i++) {
            StringBuilder stringBuilder = new StringBuilder(string);
            StringBuilder currentPermutation = new StringBuilder();
            int temp = i;
            for (int div = string.length(); div >= 1; div--) {
                int quotient = temp / div;
                int remainder = temp % div;
                currentPermutation.append(stringBuilder.charAt(remainder));
                stringBuilder.deleteCharAt(remainder);
                temp = quotient;
            }
            set.add(currentPermutation.toString());
        }
        set.stream().forEach(perm -> {
            System.out.println(perm);
        });
    }

    private int getFactorial(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    private void printPermutations(String string, String answer) {
        if (string.length() == 0) {
            System.out.println(answer + " ");
            return;
        }
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            String leftSubString = string.substring(0, i);
            String rightSubString = string.substring(i + 1);
            String rest = leftSubString + rightSubString;
            printPermutations(rest, answer + character);
        }
    }
}
