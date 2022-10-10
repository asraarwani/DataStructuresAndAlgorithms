package com.some_domain.www.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : asraar
 * @date : 10-10-2022 12:49 pm
 */
public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i <= 5; i++) {
            service.execute(new Task(i));
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("All tasks completed");
    }
}

class Task implements Runnable {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(" Starting task : " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" Completed task : " + id);
    }
}
