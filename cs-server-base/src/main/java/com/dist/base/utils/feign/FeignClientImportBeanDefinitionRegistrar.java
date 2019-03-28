package com.dist.base.utils.feign;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Set;

/**
 * 将带有@FeignClient注解注入容器中
 *
 * @author yujx
 * @date 2019/03/21 13:44
 */
public class FeignClientImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    protected static Object basePackages;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes("com.dist.base.utils.feign.EnableFeignClients");

        basePackages = annotationAttributes.get("value");

        if (basePackages != null) {

            Reflections reflections = new Reflections((String[]) basePackages);

            // 注解版
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(FeignClient.class);

            // // 继承版
            // Set<Class<? extends FeignClient>> classSet = reflections.getSubTypesOf(FeignClient.class);

            for (Class<?> aClass : classSet) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(aClass);

                AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

                beanDefinition.setBeanClass(FeignClientFactoryBean.class);
                beanDefinition.getPropertyValues().addPropertyValue("clazz", aClass);


                // 获取实现类类名，将首字母小写
                String simpleName = aClass.getSimpleName();
                String beanName = StringUtils.uncapitalize(simpleName);

                registry.registerBeanDefinition(beanName, beanDefinition);

            }
        }
    }

}
