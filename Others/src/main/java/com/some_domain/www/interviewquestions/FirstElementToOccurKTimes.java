package com.some_domain.www.interviewquestions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : asraar
 * @date : 10-10-2022 10:49 pm
 * <p>
 * Reference : https://www.geeksforgeeks.org/first-element-occurring-k-times-array/
 */
public class FirstElementToOccurKTimes {

    public static void main(String[] args) {

        int[] array = {4, 2, 2, 2, 3, 4, 4, 4, 3, 2};
        int k = 3;
        FirstElementToOccurKTimes instance = new FirstElementToOccurKTimes();

        int index = instance.findFirstElementToOccurKTimes(array, array.length, k);
        System.out.println("Index : " + index);
    }

    private int findFirstElementToOccurKTimes(int[] array, int n, int k) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : array) {
            if (map.containsKey(num)) {
                int count = map.get(num) + 1;
                if (count == k)
                    return num;
                map.put(num, count);

            } else {
                map.put(num, 1);
                if (1 == k)
                    return num;
            }
        }

        for (int i = 0; i < n; i++) {
            if (map.get(array[i]) == k)
                return array[i];
        }
        return -1;
    }

    private int findFirstElementToOccurKTimesWithTweaks(int[] array, int n, int k) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : array) {
            if (map.containsKey(num)) {
                int currentCount = map.get(num);
                if (currentCount == k)
                    return num;
                else {
                    currentCount = currentCount + 1;
                    if (currentCount == k)
                        return num;
                    else {
                        map.put(num, currentCount);
                    }
                }
            } else {
                map.put(num, 1);
                if (1 == k)
                    return num;
            }
        }

        for (int i = 0; i < n; i++) {
            if (map.get(array[i]) == k)
                return array[i];
        }
        return -1;
    }
}
