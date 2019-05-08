package com.dist.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yujx
 */
//@EnableDubbo
@EnableAsync
@EntityScan("com.dist.model.concurrent.entity")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(basePackages={"com.dist.concurrent","com.dist.base.utils"})
public class ConcurrentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcurrentApplication.class, args);
    }
}
