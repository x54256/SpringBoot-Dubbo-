package com.dist.file;

import com.dist.base.utils.feign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yujx
 */
@EnableFeignClients(value = "com.dist.file")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages={"com.dist.file","com.dist.base.utils"})
public class CommonFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonFileApplication.class, args);
    }
}
