package com.dist.concurrent.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author yujx
 * @date 2019/05/08 10:36
 */
@Service
public class CallBackService {

    @Async
    public CompletableFuture<Boolean> test() {

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int a = 1/0;
        return CompletableFuture.completedFuture(false);
    }

}
