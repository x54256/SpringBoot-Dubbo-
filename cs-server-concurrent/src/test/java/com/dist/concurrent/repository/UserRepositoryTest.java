package com.dist.concurrent.repository;

import com.dist.concurrent.service.ModelCalculationStatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yujx
 * @date 2019/04/04 11:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private ModelCalculationStatisticsServiceImpl modelCalculationStatisticsService;

    @Test
    public void func() {
        modelCalculationStatisticsService.save11();
        modelCalculationStatisticsService.save1();

        while (true) {}
    }


    @Test
    public void func2() {

        modelCalculationStatisticsService.save2();

        modelCalculationStatisticsService.save3();

        while (true) {}

    }

    // 179
    @Test
    public void func4() {

        long a = System.currentTimeMillis();

        for (Future<Integer> integerFuture : Arrays.asList(
                modelCalculationStatisticsService.save4(),
                modelCalculationStatisticsService.save5()
        )) {


            try {
                integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }


        System.out.println(System.currentTimeMillis() - a);

    }

    @Autowired
    private ModelCalculationStatisticsRepository modelCalculationStatisticsRepository;

    @Test
    public void func6() {
        modelCalculationStatisticsRepository.saveOne();
    }

    @Test
    public void func7() {
        Future<Integer> future = modelCalculationStatisticsService.func();

        future.cancel(true);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}