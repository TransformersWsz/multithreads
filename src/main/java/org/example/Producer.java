package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable{
    private Integer id;
    private List<Integer> list;
    private BlockingQueue<Integer> queue;
    public Producer(Integer id, List<Integer> list, BlockingQueue<Integer> queue) {
        this.id = id;
        this.list = list;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (this.list.size() > 0) {
            // 加锁
            synchronized (this.list) {
                // 防止其它线程先将list取空
                if (this.list.size() > 0) {
                    Integer n = this.list.remove(0);
                    try {
                        if (this.queue.offer(n, 2, TimeUnit.SECONDS)) {
                            System.out.println("Producer-" + this.id + " is producing " + n);
                        }
                        else {
                            System.out.println("Put failure, queue is full. Please wait some time");
                            Thread.sleep(200);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
