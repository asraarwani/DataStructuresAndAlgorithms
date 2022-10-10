package com.some_domain.www.threading;

public class PrintOddEvenNumbersUsingTwoThreads {

    private int counter = 1;
    private final int MAX_COUNTER = 20;

    public static void main(String[] args) {

        PrintOddEvenNumbersUsingTwoThreads instance = new PrintOddEvenNumbersUsingTwoThreads();
        Thread evenNumberPrinterThread = new Thread(() -> instance.evenNumberPrinter(), "EvenThread");
        Thread oddNumberPrinterThread = new Thread(() -> instance.oddNumberPrinter(), "OddThread");

        oddNumberPrinterThread.start();
        evenNumberPrinterThread.start();

    }


    private void evenNumberPrinter() {
        synchronized (this) {
            while (counter <= MAX_COUNTER) {
                if (counter % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " " + counter + " ");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter++;
                this.notify();
            }
        }
    }

    private void oddNumberPrinter() {
        synchronized (this) {
            while (counter <= MAX_COUNTER) {
                if (counter % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + counter + " ");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter++;
                this.notify();
            }
        }
    }
}
