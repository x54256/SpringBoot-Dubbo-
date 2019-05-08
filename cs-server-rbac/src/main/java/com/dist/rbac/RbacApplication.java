package com.dist.rbac;

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
@EntityScan("com.dist.model.rbac.entity")
@ComponentScan(basePackages={"com.dist.rbac","com.dist.base.utils"})
public class RbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class, args);
    }
}
