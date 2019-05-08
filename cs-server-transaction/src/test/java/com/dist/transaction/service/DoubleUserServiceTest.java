package com.dist.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yujx
 * @date 2019/04/12 11:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DoubleUserServiceTest {

    @Autowired
    private DoubleUserService doubleUserService;

    @Test
    public void func() {
        doubleUserService.func();
    }
}