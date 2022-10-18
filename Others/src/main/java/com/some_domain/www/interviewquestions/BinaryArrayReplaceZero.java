package com.some_domain.www.interviewquestions;

import java.util.Arrays;

/**
 * @author : asraar
 * @date : 18-10-2022 03:52 pm
 * <p>
 * Reference  : https://www.geeksforgeeks.org/find-index-0-replaced-1-get-longest-continuous-sequence-1s-binary-array/
 * https://www.ideserve.co.in/learn/index-of-0-replacing-with-1-results-in-longest-continuous-1s-sequence
 */
public class BinaryArrayReplaceZero {

    public static void main(String[] args) {

        int array[] = {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println(Arrays.toString(array));
        BinaryArrayReplaceZero instance = new BinaryArrayReplaceZero();
        instance.findIndexOfZero(array);
    }

    /*
        If the current index points to 0(say curr_0) and if we know the index which points to previous occurrence of 0 (say prev_0)
        and if we also know the index which points to previous-to-previous occurrence of 0 (say prevPrev_0)
        then subtracting (prevPrev_0 + 1) from curr_0 gives us the number of 1's that could be obtained by
        replacing 0 with 1 at index prev_0.
        This is because between indices prevPrev_0 and curr_0 there are all 1's except a single 0 at prev_0.
     */
    private void findIndexOfZero(int[] array) {
        int previousZero = -1;
        int previousPreviousZero = -1;
        int maxIndex = 0;
        int maxCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                if (i - previousPreviousZero > maxCount) {
                    maxCount = i - previousPreviousZero;
                    maxIndex = previousZero;
                }
                previousPreviousZero = previousZero;
                previousZero = i;
            }
        }
        if (array.length - previousPreviousZero > maxCount) {
            maxIndex = previousZero;
        }
        System.out.println("Index of 0 to be replaced to get longest continuous sequence of 1's : " + maxIndex);
    }
}
