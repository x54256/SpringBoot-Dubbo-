package com.dist.biz;

/**
 * @author yujx
 * @date 2019/04/17 17:32
 */
public class DeadlockDemo {

    public static void main(String[] args) {

        String lock1 = "lock1";
        String lock2 = "lock2";

        A a = new A(lock1, lock2);
        new Thread(a, "线程1").start();

        A a1 = new A(lock2, lock1);
        new Thread(a1, "线程2").start();

    }


    static class A implements Runnable {

        private String lock1;
        private String lock2;

        public void run() {

            synchronized (lock1) {
                System.out.println("线程：" + Thread.currentThread().getName() + "拿着" + lock1 + "正在获取" + lock2);

                synchronized (lock2) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "进来了！");
                }
            }

        }


        public A(String lock1, String lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

    }


}
