package com.some_domain.www.multithreadingproblem_2;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private AtomicInteger seq = new AtomicInteger();
    private int currIter = 0;
    private int parallelize = 0;

    public IdGenerator(int seq, int parallelize) {
        this.currIter = seq;
        this.parallelize = parallelize;
    }

    public int incrementAndGet() {
        int currentIncVal = seq.incrementAndGet();
        if(currentIncVal == 100) {
            currIter = currIter + parallelize;
            seq.set(0);
        }
        return (currIter * 100) + currentIncVal;
    }

}
