package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 18-10-2022 04:24 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/longest-subsegment-1s-formed-changing-k-0s/
 */
public class BinaryArrayReplaceKZeroForLongestSubsegment {

    public static void main(String[] args) {

        int array[] = {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0};
        int k = 2;
        BinaryArrayReplaceKZeroForLongestSubsegment instance = new BinaryArrayReplaceKZeroForLongestSubsegment();
        instance.getLengthOfLongestSubsegment(array, k);
    }

    private void getLengthOfLongestSubsegment(int[] array, int k) {
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (array[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, i - left + 1);
        }
        System.out.println("Length of the longest subsegment of ‘1’s possible by changing at most " + k + " ‘0’s is : " + maxLength);
    }
}
