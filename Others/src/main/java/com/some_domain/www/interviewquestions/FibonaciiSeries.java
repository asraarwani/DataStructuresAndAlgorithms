package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 01-10-2022 10:31 am
 * <p>
 * Reference : https://www.geeksforgeeks.org/different-ways-to-print-fibonacci-series-in-java/
 */
public class FibonaciiSeries {

    public static void main(String[] args) {

        int number = 20;
        FibonaciiSeries instance = new FibonaciiSeries();
        instance.printFibonacii(number);
        System.out.println("Time complexity  is O(N) for this approach");

        for (int i = 0; i < number; i++) {
            System.out.print(instance.printFibonaciiUsingRecursion(i) + " ");
        }
        System.out.println("Time complexity  is O(2^N) for this approach");

        for (int i = 0; i < number; i++) {
            System.out.print(instance.printFibonaciiUsingDynamicProgramming(i) + " ");
        }
        System.out.println("Time and space complexity for this approach is O(N)");

    }

    private void printFibonacii(int number) {
        int firstNumber = 0;
        int secondNumber = 1;
        int counter = 0;
        while (counter < number) {
            System.out.print(firstNumber + " ");
            int sum = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = sum;
            counter++;
        }
    }

    private int printFibonaciiUsingRecursion(int number) {
        if (number <= 1)
            return number;
        else
            return printFibonaciiUsingRecursion(number - 1) + printFibonaciiUsingRecursion(number - 2);
    }

    private int printFibonaciiUsingDynamicProgramming(int number) {
        int[] array = new int[number + 2];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= number; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[number];
    }
}
