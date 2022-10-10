package com.some_domain.www.threading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : asraar
 * @date : 10-10-2022 03:07 pm
 * <p>
 * CountDownLatch : CountDownLatch is a synchronization aid that allows one or
 * more threads to wait until a set of operations being performed in other threads completes.
 * The CountDownLatch is initialized to a given count and then each thread counts down
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(4);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            service.execute(new MyRunnableTask(latch));
        }
        System.out.println("All the servers are waiting to start");
        latch.await();
        System.out.println("All servers are up now , ");
    }
}

class MyRunnableTask implements Runnable {
    private CountDownLatch latch;
    private static int i = 0;

    public MyRunnableTask(CountDownLatch latch) {
        this.latch = latch;
        System.out.println("Going to start Server  " + (++i) + " is running now ");
    }

    @Override
    public void run() {
        System.out.println(" Started...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
