package com.some_domain.www.threading;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author : asraar
 * @date : 10-10-2022 10:48 am
 */
public class ProducerConsumer {

    private static LinkedList<Integer> list = new LinkedList<>();
    private static Object lock = new Object();
    private static int LIMIT = 10;

    private void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                if (list.size() == LIMIT)
                    lock.wait();
                int item = random.nextInt(100);
                System.out.println("Item added is : " + item);
                list.add(item);
                lock.notify();
            }
        }
    }

    private void consumer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if (list.size() <= 0)
                    lock.wait();

                int temp = list.removeFirst();
                System.out.println("Item removed is : " + temp);
                lock.notify();
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {

        ProducerConsumer instance = new ProducerConsumer();
        Thread producer = new Thread(() -> {
            try {
                instance.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread consumer = new Thread(() -> {
            try {
                instance.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

    }
}
