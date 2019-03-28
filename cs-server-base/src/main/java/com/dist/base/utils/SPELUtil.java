package com.dist.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.*;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.stereotype.Component;

/**
 * 针对 Spring 实现某些特殊逻辑时，支持 SPEL 表达式
 * 
 * @author x5456
 */
@Component
public class SPELUtil implements BeanPostProcessor, BeanFactoryAware, BeanClassLoaderAware {

    private static BeanFactory beanFactory;
    private static BeanExpressionResolver resolver;
    private static BeanExpressionContext expressionContext;

    /**
     * 解析 SPEL
     *
     * @param value
     * @return
     */
    public static Object resolveExpression(String value){
        if (value.startsWith("${") && value.endsWith("}")) {
            String resolvedValue = resolve(value);
            return SPELUtil.resolver.evaluate(resolvedValue, SPELUtil.expressionContext);
        }
        return value;
    }

    /**
     * 解析 ${}
     * @param value
     * @return
     */
    private static String resolve(String value){
        if (SPELUtil.beanFactory != null && SPELUtil.beanFactory instanceof ConfigurableBeanFactory) {
            return ((ConfigurableBeanFactory) SPELUtil.beanFactory).resolveEmbeddedValue(value);
        }
        return value;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        SPELUtil.resolver = new StandardBeanExpressionResolver(classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SPELUtil.beanFactory = beanFactory;
        if(beanFactory instanceof ConfigurableListableBeanFactory){
            SPELUtil.resolver = ((ConfigurableListableBeanFactory) beanFactory).getBeanExpressionResolver();
            SPELUtil.expressionContext = new BeanExpressionContext((ConfigurableListableBeanFactory) beanFactory, null);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

//    /**
//     * 对 bean 的后置处理
//     *
//     * @param bean
//     * @param beanName
//     * @return
//     * @throws BeansException
//     */
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        //获取实际类型
//        Class<?> targetClass = AopUtils.getTargetClass(bean);
//        //获取所有方法
//        ReflectionUtils.doWithMethods(targetClass, method -> {
//            //获取自定义的注解(Bean是个例子）
//            Bean annotation = AnnotationUtils.findAnnotation(method, Bean.class);
//            //假设下面的 value 支持 SPEL
//            for (String val : annotation.value()) {
//                //解析表达式
//                Object value = resolveExpression(val);
//                //TODO 其他逻辑
//            }
//        }, method -> {
//            //TODO 过滤方法
//            return true;
//        });
//        return null;
//    }
}