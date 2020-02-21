package com.some_domain.www.multithreadingproblem_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class RandomIdAssigner {

    private final Queue<IdGenerator> redQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> blueQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> greenQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> blackQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> whiteQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> yellowQueueIdGenerator = new LinkedList<>();
    private final Queue<IdGenerator> orangeQueueIdGenerator = new LinkedList<>();

    public RandomIdAssigner(int parallelize) {
        for (int i = 0; i < parallelize; i++) {
            redQueueIdGenerator.add(new IdGenerator(i, parallelize));
            blueQueueIdGenerator.add(new IdGenerator(i, parallelize));
            greenQueueIdGenerator.add(new IdGenerator(i, parallelize));
            blackQueueIdGenerator.add(new IdGenerator(i, parallelize));
            whiteQueueIdGenerator.add(new IdGenerator(i, parallelize));
            yellowQueueIdGenerator.add(new IdGenerator(i, parallelize));
            orangeQueueIdGenerator.add(new IdGenerator(i, parallelize));
        }
    }

    public Ball assignIdSem(Ball ball, Semaphore sem) {
        try {
            IdGenerator idGenerator = null;
            switch (ball.getColor().toString()) {
                case "RED":
                    sem.acquire();
                    idGenerator = redQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    redQueueIdGenerator.add(idGenerator);
                    break;
                case "GREEN":
                    sem.acquire();
                    idGenerator = greenQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    greenQueueIdGenerator.add(idGenerator);
                    break;
                case "YELLOW":
                    sem.acquire();
                    idGenerator = yellowQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    yellowQueueIdGenerator.add(idGenerator);
                    break;
                case "ORANGE":
                    sem.acquire();
                    idGenerator = orangeQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    orangeQueueIdGenerator.add(idGenerator);
                    break;
                case "BLUE":
                    sem.acquire();
                    idGenerator = blueQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    blueQueueIdGenerator.add(idGenerator);
                    break;
                case "WHITE":
                    sem.acquire();
                    idGenerator = whiteQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    whiteQueueIdGenerator.add(idGenerator);
                    break;
                case "BLACK":
                    sem.acquire();
                    idGenerator = blackQueueIdGenerator.poll();
                    ball.setId(String.valueOf(idGenerator.incrementAndGet()));
                    blackQueueIdGenerator.add(idGenerator);
                    break;
                default:
                    break;
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(ball + " -- available permits -- " + sem.availablePermits());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ball + " -- available permits -- " + sem.availablePermits());
            System.exit(1);
        } finally {
            System.out.println(ball + " -- available permits -- " + sem.availablePermits());
            sem.release();
        }
        return ball;
    }
}
