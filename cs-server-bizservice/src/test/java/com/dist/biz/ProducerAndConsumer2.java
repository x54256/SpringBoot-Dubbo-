package com.dist.biz;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 为什么主线程没有退出
 *
 * @author yujx
 * @date 2019/04/17 11:11
 */
public class ProducerAndConsumer2 {

    @Test
    public void func() {

        A a = new A();

        // 线程1负责生产
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                a.increment();
            }
        }).start();

        // 线程2负责消费
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                a.decrement();
            }
        }).start();


        System.out.println(Thread.currentThread().getName() + "结束");
    }


    private static class A {

        private Integer num = 0;

        // 代替synchronized
        private Lock lock = new ReentrantLock();

        // 代替wait、notify
        private Condition condition = lock.newCondition();

        void increment() {

            lock.lock();
            try {
                // 如果数量等于0，才进行生产
                while (num != 0) {
                    condition.await();
                }
                num++;
                System.out.println(Thread.currentThread().getName() + "：" + num);

                // 唤醒其它线程
                condition.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void decrement() {

            lock.lock();
            try {
                // 如果数量不等于0，才进行消费
                while (num == 0) {
                    condition.await();
                }
                num--;
                System.out.println(Thread.currentThread().getName() + "：" + num);

                // 唤醒其它线程
                condition.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }


}
