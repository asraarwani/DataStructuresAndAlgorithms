package com.some_domain.www.dynamicprogramming;

/**
 * @author : waniasra
 * @date : 2/2/2020 10:43 AM
 * This class demonstrates how to print the subset from an array equal to given sum
 */
//Reference : http://javaexplorer03.blogspot.com/2017/06/subset-sum-problem-dynamic-programming.html
public class PrintSubsetEqualToGivenSum {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 6};
        int sum = 13;

        PrintSubsetEqualToGivenSum instance = new PrintSubsetEqualToGivenSum();

        instance.printSubsetEqualToGivenSum(array, array.length, sum);
    }

    public void printSubsetEqualToGivenSum(int[] array, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (array[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - array[i - 1]];
                }
            }
        }
        printElements(dp, array, n, sum);
    }

    private void printElements(boolean[][] dp, int[] array, int rows, int columns) {
        int i = rows;
        int j = columns;
        while (i > 0 && j > 0) {
            int value = array[i - 1];
            if (j - array[i - 1] >= 0 && dp[i - 1][j - array[i - 1]]) {
                j = j - array[i - 1];
                System.out.print(value + " ");
            }
            i--;
        }
    }
}
