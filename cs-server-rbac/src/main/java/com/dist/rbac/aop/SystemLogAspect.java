package com.dist.rbac.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yangmin
 * @date 2017/10/29
 * @desc
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /**
     * Service层切点
     */
    @Pointcut("execution(* com.dist.rbac.service..*(..))")
    public void exceptionAspect() {
    }

    @AfterThrowing(pointcut = "exceptionAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) {
        // TODO: 2019/2/13 抛出异常前的提示
    }
}
