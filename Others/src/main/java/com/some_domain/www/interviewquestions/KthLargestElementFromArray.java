package com.some_domain.www.interviewquestions;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : asraar
 * @date : 05-10-2022 02:17 pm
 * <p>
 * Reference : https://www.youtube.com/watch?v=FrWq2rznPLQ&ab_channel=KevinNaughtonJr.
 */
public class KthLargestElementFromArray {

    public static void main(String[] args) {

        int[] array = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;

        KthLargestElementFromArray instance = new KthLargestElementFromArray();
        int kthLargestElement = instance.findKthLargestNumber(array, k);
        System.out.println(k + "-th largest element from array is " + kthLargestElement + ". Time complexity is O(NLogN)");

        kthLargestElement = instance.findKthLargestNumberUsingHeap(array, k);
        System.out.println(k + "-th largest element from array is " + kthLargestElement + ". Time and space complexity is O(N)");
    }

    private int findKthLargestNumber(int[] array, int k) {
        Arrays.sort(array);
        return array[array.length - k];
    }

    private int findKthLargestNumberUsingHeap(int[] array, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int number : array) {
            minHeap.add(number);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.remove();
    }
}
