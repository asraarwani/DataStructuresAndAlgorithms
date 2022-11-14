package com.some_domain.www.interviewquestions;

/**
 * @author : asraar
 * @date : 14-11-2022 03:11 pm
 */
public class ValidMountainArrayCheck {

    public static void main(String[] args) {

        int[] array = {0, 3, 2, 1};
        //int[] array = {3, 5, 5};
        //int[] array = {0 ,1, 2 ,4 ,3, 2, 1};
        ValidMountainArrayCheck instance = new ValidMountainArrayCheck();
        boolean isValidMountainArray = instance.isValidMountainArray(array);
        System.out.println("Given array is a valid mountain array  : " + isValidMountainArray);
    }

    //Where there is increasing and decreasing sequence in array- 0 1 2 4 3 2 1
    private boolean isValidMountainArray(int[] array) {
        int i = 0;
        //If array in increasing...
        while (i < array.length && i + 1 < array.length && array[i] < array[i + 1]) {
            i++;
        }

        //If it was never increasing or it is only increasing
        if (i == 0 || i + 1 >= array.length) {
            return false;
        }

        //We had increasing sequence, now we will check for decreasing sequence
        while (i < array.length && i + 1 < array.length) {
            if (array[i] <= array[i++ + 1]) {
                return false;
            }
        }

        return true;
    }
}
