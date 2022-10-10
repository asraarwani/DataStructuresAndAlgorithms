package com.some_domain.www.threading;

/**
 * @author : asraar
 * @date : 10-10-2022 02:01 pm
 */
public class DeadLockExample {

    public static void main(String[] args) {

        final Object lock1 = new Object();
        final Object lock2 = new Object();

        FirstThread firstThread = new FirstThread(lock1, lock2);
        SecondThread secondThread = new SecondThread(lock1, lock2);

        firstThread.start();
        secondThread.start();

    }
}

class FirstThread extends Thread {

    private Object lock1;
    private Object lock2;

    FirstThread(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("T1 holding Lock 1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 waiting for Lock 2");
            synchronized (lock2) {
                System.out.println("T1 holding Lock 1 and 2");
            }
        }
    }
}

class SecondThread extends Thread {

    private Object lock1;
    private Object lock2;

    SecondThread(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock2) {
            System.out.println("T2 holding Lock 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 waiting for Lock 1");
            synchronized (lock1) {
                System.out.println("T2 holding Lock 1 and 1");
            }
        }
    }
}
