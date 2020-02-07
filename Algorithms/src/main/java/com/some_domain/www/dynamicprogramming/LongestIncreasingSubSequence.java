package com.some_domain.www.dynamicprogramming;

/**
 * @author : waniasra
 * @date : 2/7/2020 12:43 PM
 * This class demonstrates how to find the longest increasing sub-sequence in NlogN time
 */
//https://www.ideserve.co.in/learn/longest-increasing-subsequence-nlogn
public class LongestIncreasingSubSequence {

    public static void main(String args[]) {
        int array[] = {2, 5, 3, 7, 11, 8, 10, 13, 6};
        LongestIncreasingSubSequence longestIncreasingSubSequence = new LongestIncreasingSubSequence();
        System.out.println("Time complexity is O(NlogN)");
        longestIncreasingSubSequence.printLongestIncreasingSubSequence(array);
    }

    public void printLongestIncreasingSubSequence(int[] array) {
        int[] parent = new int[array.length];
        int[] increasingSub = new int[array.length];
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            int low = 1;
            int high = length;
            while (low <= high) {
                int mid = (int) Math.ceil((low + high) / 2);
                if (array[increasingSub[mid]] < array[i])
                    low = mid + 1;
                else
                    high = mid - 1;
            }

            int pos = low;
            parent[i] = increasingSub[pos - 1];
            increasingSub[pos] = i;
            if (pos > length)
                length = pos;
        }

        int[] longestIncreasingSubSequence = new int[length];
        int k = increasingSub[length];
        for (int j = length - 1; j >= 0; j--) {
            longestIncreasingSubSequence[j] = array[k];
            k = parent[k];
        }

        for (int i = 0; i < longestIncreasingSubSequence.length; i++)
            System.out.print(longestIncreasingSubSequence[i] + " ");
    }
}

