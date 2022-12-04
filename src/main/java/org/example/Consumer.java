package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{
    private Integer id;
    private List<Integer> list;
    private BlockingQueue<Integer> queue;

    public Consumer(Integer id, List<Integer> list, BlockingQueue<Integer> queue) {
        this.id = id;
        this.list = list;
        this.queue = queue;
    }

    @Override
    public void run() {
        // 队列为空且元素列表为空，则生产已经结束
        while (!(this.queue.size() == 0 && this.list.size() == 0)) {
            try {
                Integer n = this.queue.poll(2, TimeUnit.SECONDS);
                if (n != null) {
                    System.out.println("Consumer-" + this.id + " is consuming " + n);
                }
                else {
                    if (this.list.size() == 0 && this.list.size() == 0) {
                        break;
                    }
                    else {
                        System.out.println("Producer is producing, please wait!");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("End Producing, " + "Consumer-" + this.id + " exit now");
    }
}
