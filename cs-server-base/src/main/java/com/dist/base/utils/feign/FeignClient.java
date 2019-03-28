package com.dist.base.utils.feign;

import java.lang.annotation.*;

/**
 * 标识接口由Feign代理
 *
 * @author yujx
 * @date 2019/03/21 13:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignClient {

    /**
     * 注解的类对应的请求url，支持el表达式
     *
     * @return
     */
    String value();
}
