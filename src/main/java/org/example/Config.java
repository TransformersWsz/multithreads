package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Config {
    // 要生产的元素个数
    private Integer elementNum;
    // 生产者个数
    private Integer producerNum;
    // 生产者线程池大小
    private Integer producerPoolSize;
    // 消费者个数
    private Integer consumerNum;
    // 生产者线程池大小
    private Integer consumerPoolSize;
    // 阻塞队列大小
    private Integer queueSize;

    public void info() {
        String line = String.format("elementNum=%s, producerNum=%s, producerPoolSize=%s, " +
                "consumerNum=%s, consumerPoolSize=%s, queueSize=%s",
                elementNum, producerNum, producerPoolSize,
                consumerNum, consumerPoolSize, queueSize);
        System.out.println(line);
    }
}
