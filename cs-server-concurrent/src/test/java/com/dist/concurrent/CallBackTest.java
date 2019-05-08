package com.dist.concurrent;

import com.dist.concurrent.service.CallBackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertFalse;

/**
 * @author yujx
 * @date 2019/05/08 10:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CallBackTest {

    @Autowired
    private CallBackService callBackService;

    @Test
    public void test() {

        CompletableFuture<Object> completableFuture = callBackService.test()
                .thenApply(x -> {
                    if (x) {
                        System.out.println("xxx");
                        return 1;
                    } else {
                        System.out.println("yyy");
                        throw new RuntimeException("1232");
//                        return "2";
                    }
                });


        try {
            System.out.println("123213232123213:" + completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        while (true) {

        }

    }

    public static void main(String[] args) {
        thenApplyExample();
    }

    static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
        try {
            System.out.println(cf.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
