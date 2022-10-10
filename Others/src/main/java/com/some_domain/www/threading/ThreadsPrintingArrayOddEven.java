package com.some_domain.www.threading;

/**
 * @author : asraar
 * @date : 10-10-2022 11:31 am
 */
public class ThreadsPrintingArrayOddEven {

    public static void main(String[] args) throws InterruptedException {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final Object mutex = new Object();

        OddElementPrinter oddElementPrinter = new OddElementPrinter(array, mutex);
        EvenElementPrinter evenElementPrinter = new EvenElementPrinter(array, mutex);

        Thread oddElementPrinterThread = new Thread(oddElementPrinter, "Thread 2");
        Thread evenElementPrinterThread = new Thread(evenElementPrinter, "Thread 1 ");


        evenElementPrinterThread.start();
        oddElementPrinterThread.start();
    }
}

class EvenElementPrinter implements Runnable {

    private int[] array;
    private final Object mutex;
    private int index = 0;

    public EvenElementPrinter(int[] array, Object mutex) {
        this.array = array;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            synchronized (mutex) {
                if (index < array.length) {
                    System.out.println(Thread.currentThread().getName() + " printed : " + array[index]);
                    index += 2;
                    mutex.notify();
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class OddElementPrinter implements Runnable {

    private int[] array;
    private final Object mutex;
    private int index = 1;

    public OddElementPrinter(int[] array, Object mutex) {
        this.array = array;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            synchronized (mutex) {
                if (index < array.length) {
                    System.out.println(Thread.currentThread().getName() + "  printed : " + array[index]);
                    index += 2;
                    mutex.notify();
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

