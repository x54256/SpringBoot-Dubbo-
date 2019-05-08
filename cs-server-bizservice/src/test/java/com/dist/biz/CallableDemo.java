package com.dist.biz;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yujx
 * @date 2019/04/17 14:52
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(3);


//        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10086);
//        new Thread(futureTask).start();
//        while (!futureTask.isDone()) {}
//        System.out.println(futureTask.get());


        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
