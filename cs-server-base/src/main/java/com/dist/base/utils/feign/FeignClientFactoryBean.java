package com.dist.base.utils.feign;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 生产代理对象
 *
 * @author yujx
 * @date 2019/03/21 13:56
 */
public class FeignClientFactoryBean implements FactoryBean {

    private Class clazz;

    @Override
    public Object getObject() throws Exception {

        return Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new FeignInvocationHandler()
        );
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
