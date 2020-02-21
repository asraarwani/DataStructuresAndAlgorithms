package com.some_domain.www.multithreadingproblem_1;

import java.util.concurrent.atomic.AtomicInteger;

public class RandomIdAssigner {

    private final static AtomicInteger redBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger blueBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger greenBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger blackBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger whiteBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger yellowBucketIdGenerator = new AtomicInteger();
    private final static AtomicInteger orangeBucketIdGenerator = new AtomicInteger();


    public Ball assignId(Ball ball, ColorLock lock) {
        synchronized (lock) {
            bucketIdGenerator(ball, lock);
            ball.setDesc(ball.getId() + " Desc");
        }
        return ball;
    }

    public void bucketIdGenerator(Ball ball, ColorLock colorLock) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (colorLock.toString().equalsIgnoreCase("RED_LOCK")) {
            ball.setId(String.valueOf(redBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("GREEN_LOCK")) {
            ball.setId(String.valueOf(greenBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("YELLOW_LOCK")) {
            ball.setId(String.valueOf(yellowBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("ORANGE_LOCK")) {
            ball.setId(String.valueOf(orangeBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("BLACK_LOCK")) {
            ball.setId(String.valueOf(blackBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("BLUE_LOCK")) {
            ball.setId(String.valueOf(blueBucketIdGenerator.getAndIncrement()));
        } else if (colorLock.toString().equalsIgnoreCase("WHITE_LOCK")) {
            ball.setId(String.valueOf(whiteBucketIdGenerator.getAndIncrement()));
        }
    }
}
