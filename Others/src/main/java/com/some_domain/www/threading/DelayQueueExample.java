package com.some_domain.www.threading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


/**
 * @author : asraar
 * @date : 10-10-2022 02:29 pm
 */
public class DelayQueueExample {

    // For BlockingQueue, DelayQueue is one of the implementation classes.
    // DelayQueue is unbound and is backed by heap, the Delayed item whose delay time expires first, is removed first and so on
    private BlockingQueue<DelayItem> queue = new DelayQueue<>();

    public void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            int delay = random.nextInt(1000);
            DelayItem delayItem = new DelayItem("Item " + delay, delay);
            queue.put(delayItem);
            System.out.println("Item Inserted : " + delayItem);
            Thread.sleep(500);
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            //DelayItem delayItem = queue.take();
            DelayItem delayItem = queue.poll();
            System.out.println("Item Removed : " + delayItem);
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        DelayQueueExample instance = new DelayQueueExample();

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

class DelayItem implements Delayed {

    private String data;
    private long startTime;

    public DelayItem(String data, long startTime) {

        this.data = data;
        this.startTime = startTime;
    }

    public String getData() {
        return data;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public int compareTo(Delayed delayedObject) {

        if (this.getStartTime() > ((DelayItem) delayedObject).getStartTime())
            return 1;
        else if (this.getStartTime() < ((DelayItem) delayedObject)
                .getStartTime())
            return -1;
        else
            return 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long difference = startTime - System.currentTimeMillis();
        return unit.convert(difference, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "DelayItem [data=" + data + ", startTime=" + startTime + "]";
    }
}

