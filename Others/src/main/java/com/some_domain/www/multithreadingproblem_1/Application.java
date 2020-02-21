package com.some_domain.www.multithreadingproblem_1;

import java.util.*;
import java.util.concurrent.*;

 /*
  Assign color to balls and then pushing them to the respective buckets where id of each ball in a bucket is unique
  using multiple threads
 */
public class Application {

    private static List<Ball> ballList = new ArrayList<>();
    private static final Object lock = new Object();


    private static void initializeBallList() {
        for (int i = 0; i < 10000; i++) {
            ballList.add(new Ball());
        }
    }

    public static void main(String[] args) {

        initializeBallList();

        final RandomIdAssigner randomIdAssigner = new RandomIdAssigner();

        DataStructure dataStructure = new DataStructure();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Ball> completionService = new ExecutorCompletionService<>(executorService);

        Map<String, List<Map<String, Ball>>> map = dataStructure.getDs();

        for (Ball ball : ballList) {
            assignRandomColor(ball);
        }

        for (Ball ball : ballList) {
            ColorLock colorLock = pickColorLock(ball);
            if (colorLock != null) {
                Callable<Ball> call = () -> randomIdAssigner.assignId(ball, colorLock);
                completionService.submit(call);
            }
        }

        for (Ball ball : ballList) {
            try {
                Ball b = completionService.take().get();
                if (!map.containsKey(b.getColor().toString())) {
                    List<Map<String, Ball>> list = new ArrayList<>();
                    map.put(b.getColor().toString(), list);
                }
                List<Map<String, Ball>> list = map.get(b.getColor().toString());
                Map<String, Ball> ballMap = new LinkedHashMap<>();
                ballMap.put(b.getId(), b);
                System.out.println(b);
                list.add(ballMap);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    private static void assignRandomColor(Ball ball) {
        Random random = new Random();
        BallColor[] ballColors = BallColor.values();
        BallColor ballColor = ballColors[random.nextInt(7)];
        ball.setColor(ballColor);
    }

    private static ColorLock pickColorLock(Ball ball) {
        if (ball.getColor().toString() == "RED") {
            return ColorLock.RED_LOCK;
        } else if (ball.getColor().toString() == "GREEN") {
            return ColorLock.GREEN_LOCK;
        } else if (ball.getColor().toString() == "YELLOW") {
            return ColorLock.YELLOW_LOCK;
        } else if (ball.getColor().toString() == "ORANGE") {
            return ColorLock.ORANGE_LOCK;
        } else if (ball.getColor().toString() == "BLACK") {
            return ColorLock.BLACK_LOCK;
        } else if (ball.getColor().toString() == "BLUE") {
            return ColorLock.BLUE_LOCK;
        } else if (ball.getColor().toString() == "WHITE") {
            return ColorLock.WHITE_LOCK;
        }
        return null;
    }
}
