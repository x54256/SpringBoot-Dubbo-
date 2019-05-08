package com.dist.biz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yujx
 * @date 2019/04/17 14:43
 */
public class ProducerAndConsumer3 {

    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    public static void main(String[] args) {

        // 创建一个生产者
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println("生产者生产成功状态：" + blockingQueue.offer(i, 2, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        // 消费者
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    System.out.println("消费者消费了：" + blockingQueue.poll(2, TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
