package com.some_domain.www.threading;

import java.util.LinkedList;

/**
 * @author : asraar
 * @date : 10-10-2022 11:26 am
 * <p>
 * One thread continuously populating the list and two thread continuously
 * reading from it. One thread reads even numbers and one reads odd numbers.
 */
public class OneProducerTwoOddEvenConsumers {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        final Object mutex = new Object();

        Producer producer = new Producer(mutex, list);
        OddConsumer oddConsumer = new OddConsumer(mutex, list);
        EvenConsumer evenConsumer = new EvenConsumer(mutex, list);

        Thread producerThread = new Thread(producer);
        Thread oddConsumerThread = new Thread(oddConsumer, "Thread 2 ");
        Thread evenConsumerThread = new Thread(evenConsumer, "Thread 1 ");

        producerThread.start();
        oddConsumerThread.start();
        evenConsumerThread.start();

    }
}

class Producer implements Runnable {
    private final Object mutex;
    private LinkedList<Integer> list;

    public Producer(Object mutex, LinkedList<Integer> list) {
        this.mutex = mutex;
        this.list = list;
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            synchronized (mutex) {
                list.add(i++);
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

class EvenConsumer implements Runnable {

    private final Object mutex;
    private LinkedList<Integer> list;
    int index = 0;

    public EvenConsumer(Object mutex, LinkedList<Integer> list) {
        this.mutex = mutex;
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                if (index < list.size()) {
                    int item = list.get(index);
                    index += 2;
                    System.out.println(Thread.currentThread().getName() + " Consumed " + item);
                    mutex.notifyAll();
                    try {
                        Thread.sleep(500);
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class OddConsumer implements Runnable {

    private final Object mutex;
    private LinkedList<Integer> list;
    int index = 1;

    public OddConsumer(Object mutex, LinkedList<Integer> list) {
        this.mutex = mutex;
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (mutex) {
                if (index < list.size()) {
                    int item = list.get(index);
                    index += 2;
                    System.out.println(Thread.currentThread().getName() + " Consumed " + item);
                    mutex.notifyAll();
                    try {
                        Thread.sleep(500);
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
