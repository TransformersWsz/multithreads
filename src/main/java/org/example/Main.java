package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Integer listSize = Integer.parseInt(args[0]);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(i);
        }
        Integer queueSize = Integer.parseInt(args[1]);
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(queueSize);

//        ExecutorService service = Executors.newCachedThreadPool();
        Integer producers =  Integer.parseInt(args[2]);
        Integer consumers =  Integer.parseInt(args[3]);
        for (int i = 0; i < producers; i++) {
            new Thread(new Producer(i, list, blockingQueue)).start();
        }
        for (int i = 0; i < consumers; i++) {
            new Thread(new Consumer(i, list, blockingQueue)).start();
        }
    }
}