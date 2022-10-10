package com.some_domain.www.threading;

import java.util.concurrent.*;

/**
 * @author : asraar
 * @date : 10-10-2022 12:54 pm
 */
public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 10; i++) {
            Future<String> future = service.submit(new CallableProcessor("Task " + i, 1000 + i));
            System.out.println(future.get());
        }
        service.shutdown();
    }
}

class CallableProcessor implements Callable<String> {

    private String name;
    private int timeToWait;

    public CallableProcessor(String name, int timeToWait) {

        this.name = name;
        this.timeToWait = timeToWait;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " started task " + name);
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished task " + name);
        return "R=" + timeToWait;
    }
}
