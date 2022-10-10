package com.some_domain.www.threading;

import java.text.SimpleDateFormat;

/**
 * @author : asraar
 * @date : 10-10-2022 01:01 pm
 * <p>
 * In multi-threaded environment , all the threads access the properties of the
 * * object and if the property(ies) aren't thread-safe, we use synchronization
 * * and if we don't want to use synchronization, we can use ThreadLocal
 * * variables. Each thread has its own local copy and no other thread can have
 * * access to it. Normally, these the threadLocal variables are marked as private
 * * ,static and final and a thread uses set() and get() methods to update the
 * * values.In the following program, each thread get a default Date Formatter and
 * * later changes it.
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        MyThreadLocal myThreadLocal = new MyThreadLocal();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(myThreadLocal, " " + i);
            Thread.sleep(3000);
            thread.start();
        }
    }
}

class MyThreadLocal implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> format = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy MM dd HH mm"));

    @Override
    public void run() {
        System.out.println(" Thread Name " + Thread.currentThread().getName() + " Default Formatter : " + format.get().toPattern());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        format.set(new SimpleDateFormat());
        System.out.println(" Thread Name " + Thread.currentThread().getName() + " New Formatter : " + format.get().toPattern());
    }
}
