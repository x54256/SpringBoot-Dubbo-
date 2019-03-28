package com.dist.oauth2.config;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


/**
 * dozer配置类
 * @author yujx
 * @date 2019/02/12 12:59
 */
@Configuration
public class DozerMapperConfig {

    @Bean
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozer/*.xml") Resource[] resources) {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }

}