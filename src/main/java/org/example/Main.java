package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        Config config = (Config) context.getBean("configBean");
        config.info();

        List<Integer> goods = new ArrayList<>();
        // 填充商品元素
        for (int i = 0; i < config.getElementNum(); i++) {
            goods.add(i);
        }

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(config.getQueueSize());

        ExecutorService producerService = Executors.newFixedThreadPool(config.getProducerPoolSize());
        for (int i = 0; i < config.getProducerNum(); i++) {
            producerService.execute(new Producer(i, goods, blockingQueue));
        }

        ExecutorService consumerService = Executors.newFixedThreadPool(config.getConsumerPoolSize());
        for (int i = 0; i < config.getConsumerNum(); i++) {
            consumerService.execute(new Consumer(i, goods, blockingQueue));
        }

        // 这里只是让线程池不再接收新的任务，不代表等待任务结束
        producerService.shutdown();
        consumerService.shutdown();
        // 这里可能会先执行
        System.out.println("gggggg");

        while (true) {
            try {
                // 一般shutdown和awaitTermination配套使用
                if (!producerService.awaitTermination(2, TimeUnit.SECONDS)) {
                }
                else {
                    System.out.println("All produce tasks end!");
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while (true) {
            try {
                if (!consumerService.awaitTermination(2, TimeUnit.SECONDS)) {
                }
                else {
                    System.out.println("All consume tasks end!");
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}