package com.some_domain.www.multithreadingproblem_2;

import java.util.*;
import java.util.concurrent.*;

/*
 Assign color to balls and then pushing them to the respective buckets where id of each ball in a bucket is unique
 using multiple threads (Using semaphores - so that multiple threads can access the same bucket at the same time)
*/
public class Application {

    private static List<Ball> ballList = new ArrayList<>();
    private static final Object lock = new Object();
    private static final Map<String, Semaphore> map = new LinkedHashMap<>();

    private static void initializeBallList() {
        for (int i = 0; i < 10000; i++) {
            ballList.add(new Ball());
        }
    }

    public static void main(String[] args) {

        int parallelize = 10;


        populateSemaphores(parallelize);

        initializeBallList();

        final RandomIdAssigner randomIdAssigner = new RandomIdAssigner(parallelize);

        DataStructure dataStructure = new DataStructure();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Ball> completionService = new ExecutorCompletionService<>(executorService);

        Map<String, List<Map<String, Ball>>> map = dataStructure.getDs();

        for (Ball ball : ballList) {
            assignRandomColor(ball);
        }

        for (Ball ball : ballList) {
            Callable<Ball> call = () -> randomIdAssigner.assignIdSem(ball, pickColorSemaphore(ball));
            completionService.submit(call);
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

    private static Semaphore pickColorSemaphore(Ball ball) {
        return map.get(ball.getColor().toString());
    }

    private static void populateSemaphores(int parallelize) {
        map.put("RED", new Semaphore(parallelize));
        map.put("GREEN", new Semaphore(parallelize));
        map.put("YELLOW", new Semaphore(parallelize));
        map.put("ORANGE", new Semaphore(parallelize));
        map.put("BLACK", new Semaphore(parallelize));
        map.put("BLUE", new Semaphore(parallelize));
        map.put("WHITE", new Semaphore(parallelize));
    }
}
