package com.some_domain.www.threading;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : asraar
 * @date : 10-10-2022 02:04 pm
 */
public class LockExample {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        Random random = new Random();
        try {
            lock.lock();
            while (true) {
                if (list.size() == LIMIT)
                    condition.await();
                int item = random.nextInt(100);
                list.add(item);
                System.out.println("Item added is : " + item);
                condition.signal();
                Thread.sleep(20);
            }
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        Random random = new Random();
        try {
            lock.lock();
            while (true) {
                if (list.size() == 0)
                    condition.await();
                if (random.nextInt(10) == 0) {
                    int item = list.removeFirst();
                    System.out.println("Item removed is : " + item);
                    condition.signal();
                }
                Thread.sleep(20);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LockExample instance = new LockExample();
        Thread producer = new Thread(() -> {
            try {
                instance.producer();
            } catch (InterruptedException e) {
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                instance.consumer();
            } catch (InterruptedException e) {
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
