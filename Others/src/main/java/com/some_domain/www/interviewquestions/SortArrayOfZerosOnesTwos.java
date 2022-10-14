package com.some_domain.www.interviewquestions;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : asraar
 * @date : 14-10-2022 08:36 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
 */
public class SortArrayOfZerosOnesTwos {

    public static void main(String[] args) {

        int array[] = new int[]{0, 2, 1, 2, 0};
        SortArrayOfZerosOnesTwos instance = new SortArrayOfZerosOnesTwos();
        instance.sortArrayUsingHeap(array);

        System.out.println();
        array = new int[]{0, 2, 1, 2, 0};
        instance.sortArray(array);


        instance.sortArrayOptimized(array);
        System.out.println("Time complexity is O(N)");
    }

    /*
    Keep three counters c0 to count 0s, c1 to count 1s, and c2 to count 2s

    Traverse through the array and increase the count of c0 if the element is 0,
    increase the count of c1 if the element is 1 and increase the count of c2 if the element is 2

    Now again traverse the array and replace the first c0 elements with 0, the next c1 elements with 1,
    and the next c2 elements with 2.
    */
    private void sortArrayOptimized(int[] array) {
        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;
        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case 0:
                    count_0++;
                    break;
                case 1:
                    count_1++;
                    break;
                case 2:
                    count_2++;
                    break;
            }
        }
        int i = 0;
        while (count_0 > 0) {
            array[i] = 0;
            i++;
            count_0--;
        }
        while (count_1 > 0) {
            array[i] = 1;
            i++;
            count_1--;
        }
        while (count_2 > 0) {
            array[i] = 2;
            i++;
            count_2--;
        }
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));
    }

    private void sortArrayUsingHeap(int[] array) {
        PriorityQueue minHeap = new PriorityQueue();
        for (int num : array) {
            minHeap.add(num);
        }
        minHeap.stream().forEach(item -> System.out.print(item + " "));
        System.out.println("Time complexity is o(LogN)");
    }

    private void sortArray(int[] array) {
        Arrays.sort(array);
        Arrays.stream(array).forEach(i -> System.out.print(i + " "));
        System.out.println("Time complexity is o(NLogN)");
    }
}
