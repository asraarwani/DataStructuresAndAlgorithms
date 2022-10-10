package com.some_domain.www.threading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : asraar
 * @date : 10-10-2022 02:48 pm
 * <p>
 * Barrier : A barrier is a Synchronization method that halts the progress of one or more threads at a particular point
 * CyclicBarrier : CyclicBarrier is a synchronization aid which allows one or more threads to reach a common barrier.
 * The barrier is called cyclic, because it can be reset after the waiting threads are released.
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new RunnableTask(cyclicBarrier));
            thread.start();
        }
    }
}

class RunnableTask implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private static int i = 0;

    public RunnableTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Started..");
        try {
            Thread.sleep(5000);
            cyclicBarrier.await();
            System.out.println("Thread " + (++i) + " is waiting...");
            Thread.sleep(5000);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("All are running.....");
    }
}
