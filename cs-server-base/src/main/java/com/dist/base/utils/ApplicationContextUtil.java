package com.dist.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class ApplicationContextUtil implements ApplicationContextAware {

    protected static ApplicationContext applicationContext;

    protected static DefaultListableBeanFactory beanDefinitionRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;

        ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) applicationContext;
        ApplicationContextUtil.beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext.getBeanFactory();
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String className, Class<T> clazz) throws BeansException {
        return applicationContext.getBean(className, clazz);
    }
}