package com.dist.base.utils.feign;

import com.dist.base.utils.SPELUtil;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.okhttp.OkHttpClient;
import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// FIXME: 2019/3/27 是在不知道怎么写，使用了静态变量

/**
 * Feign动态代理处理器
 *
 * @author yujx
 * @date 2019/03/21 14:00
 */
public class FeignInvocationHandler implements InvocationHandler {

    // 不需要JacksonDecoder的返回值类型
    private static final List<Class> DOES_NOT_DECODE_TYPE = Arrays.asList(
            Integer.class,
            String.class,
            Double.class,
            Boolean.class,
            Void.class
    );

    private final Object[] basePackages;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1、根据spel表达式获取请求的地址

        // 1.1 获取代理对象的真实类属性
        Class<?> clazz = method.getDeclaringClass();

        // 1.2 获取FeignClient注解
        FeignClient annotation = clazz.getAnnotation(FeignClient.class);
        // 如果获取到的注解为空，则去它的子类中找
        if (annotation == null) {
            Reflections reflections = new Reflections(basePackages);
            for (Class<?> aClass : reflections.getSubTypesOf(clazz)) {
                FeignClient childAnnotation = aClass.getAnnotation(FeignClient.class);
                if (childAnnotation != null) {
                    annotation = childAnnotation;
                    break;
                }
            }
        }

        if (annotation != null) {
            // 1.3 获取请求的url
            String requestUrl = annotation.value();
            requestUrl = String.valueOf(SPELUtil.resolveExpression(requestUrl));

            // 获取方法的返回值
            Class<?> returnType = method.getReturnType();

            // 根据返回值的不同构造不同的Feign客户端对象
            Object target;
            if (DOES_NOT_DECODE_TYPE.contains(returnType) || returnType.isPrimitive()) {
                target = Feign.builder()
                        .client(new OkHttpClient())
                        .contract(new JAXRSContract())
                        .encoder(new JacksonEncoder())
                        .target(clazz, requestUrl);

            } else {
                target = Feign.builder()
                        .client(new OkHttpClient())
                        .contract(new JAXRSContract())
                        .encoder(new JacksonEncoder())
                        .decoder(new JacksonDecoder())
                        .target(clazz, requestUrl);
            }
            // 获取参数的字节码属性数组
            List<Class> argsClass = new ArrayList<>();
            if (args != null) {
                for (Object arg : args) {
                    argsClass.add(arg.getClass());
                }
            }
            Class[] classes = argsClass.toArray(new Class[argsClass.size()]);

            // 获取代理方法
            Method proxyMethod = target.getClass().getMethod(method.getName(), classes);

            // 执行
            return proxyMethod.invoke(target, args);
        }

        throw new RuntimeException("请检查@FeignClient注解中的url是否正确！");
    }

    FeignInvocationHandler(Object[] basePackages) {
        this.basePackages = basePackages;
    }
}
