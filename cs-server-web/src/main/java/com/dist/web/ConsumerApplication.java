package com.dist.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.dist.base.utils.feign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yujx
 */
@EnableDubbo
@SpringBootApplication
@EnableFeignClients("com.dist.web.client")
@ComponentScan(basePackages={"com.dist.web","com.dist.base.utils"})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
