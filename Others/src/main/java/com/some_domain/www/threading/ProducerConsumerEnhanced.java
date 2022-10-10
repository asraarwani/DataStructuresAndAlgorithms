package com.some_domain.www.threading;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : asraar
 * @date : 10-10-2022 11:21 am
 */
public class ProducerConsumerEnhanced {

    private BlockingDeque<Integer> queue = new LinkedBlockingDeque<>(10);

    public static void main(String[] args) {

        ProducerConsumerEnhanced instance = new ProducerConsumerEnhanced();

        Thread producerThread = new Thread(() -> instance.producer());
        Thread consumerThread = new Thread(() -> instance.consumer());

        producerThread.start();
        consumerThread.start();

    }

    private void producer() {
        Random random = new Random();
        while (true) {
            int item = random.nextInt(100);
            try {
                queue.put(item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Item added is : " + item);
        }
    }

    private void consumer() {
        while (true) {
            try {
                Thread.sleep(200);
                int item = queue.take();
                System.out.println("Item taken is : " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
