package com.dist.biz;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yujx
 */
@EnableDubbo
@SpringBootApplication
@EntityScan("com.dist.model.biz.entity")
@ComponentScan(basePackages={"com.dist.biz","com.dist.base.utils"})
public class BizServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BizServiceApplication.class, args);
    }
}
