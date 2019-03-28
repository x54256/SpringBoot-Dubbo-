package com.dist.search;

import com.dist.base.utils.feign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yujx
 */
@EnableFeignClients(value = "com.dist.search")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(basePackages={"com.dist.search","com.dist.base.utils"})
public class CommonSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonSearchApplication.class, args);
    }
}
