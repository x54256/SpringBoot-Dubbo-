package com.dist.base.utils.feign;

import com.dist.base.utils.SPELUtil;
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

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(EnableFeignClients.class.getName());

        Object basePackages = annotationAttributes.get("value");
        if (basePackages != null) {

            Reflections reflections = new Reflections((Object[]) basePackages);

            // 注解版
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(FeignClient.class);

            // // 继承版
            // Set<Class<? extends FeignClient>> classSet = reflections.getSubTypesOf(FeignClient.class);

            for (Class<?> aClass : classSet) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(aClass);

                AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

                FeignClient annotation = aClass.getAnnotation(FeignClient.class);
                String requestUrl = annotation.value();
                requestUrl = String.valueOf(SPELUtil.resolveExpression(requestUrl));

                if (StringUtils.isBlank(requestUrl)) {
                    throw new RuntimeException("请检查@FeignClient注解中请求url配置。");
                }

                beanDefinition.setBeanClass(FeignClientFactoryBean.class);
                beanDefinition.getPropertyValues().addPropertyValue("clazz", aClass);
                beanDefinition.getPropertyValues().addPropertyValue("requestUrl", requestUrl);


                // 获取实现类类名，将首字母小写
                String simpleName = aClass.getSimpleName();
                String beanName = StringUtils.uncapitalize(simpleName);

                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        }
    }

}
