package com.some_domain.www.interviewquestions;

public class FloydsTriange {

    public static void main(String[] args) {

        FloydsTriange instance =new FloydsTriange();
        instance.printFloydsTriange(5);
    }

    private void printFloydsTriange(int number) {
        int a = 1;
        for (int i = 0; i <= number; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(a + " ");
                a++;
            }
            System.out.println();
        }
    }
}
