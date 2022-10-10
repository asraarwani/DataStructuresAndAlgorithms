package com.some_domain.www.threading;

/**
 * @author : asraar
 * @date : 10-10-2022 10:37 am
 */

public class Synchronization {

    public static void main(String[] args) {

        TableGenerator tableGenerator = new TableGenerator();
        Thread firstThread = new Thread(() -> tableGenerator.generateTable(5));
        Thread secondThread = new Thread(() -> tableGenerator.generateTable(10));
        //Thread firstThread = new Thread(() -> tableGenerator.generateTableUsingSynchronizedBlock(5));
        //Thread secondThread = new Thread(() -> tableGenerator.generateTableUsingSynchronizedBlock(10));
        firstThread.start();
        secondThread.start();
    }

}

class TableGenerator {
    public synchronized void generateTable(int n) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " * " + i + " = " + (n * i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateTableUsingSynchronizedBlock(int n) {
        synchronized (this) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(n + " * " + i + " = " + (n * i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
