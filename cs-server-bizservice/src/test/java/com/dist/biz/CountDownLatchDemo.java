package com.dist.biz;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @author yujx
 * @date 2019/04/17 09:20
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "天，过去了");
                // 做完事情，拿下一把锁
                countDownLatch.countDown();
            }, Objects.requireNonNull(SeasonEnum.getSeasonDesc(i))).start();
        }

        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + "新的一年开始了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
