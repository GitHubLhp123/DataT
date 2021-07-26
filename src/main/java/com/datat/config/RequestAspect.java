package com.datat.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc 配置aop拦截策略
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {
    /**
     * RequestLog
     *
     * @desc 配置aop拦截路径
     */
    @Pointcut("execution(public * com.datat.*.controller.*.*(..))")
    public void RequestLog() {
    }

    /**
     * deBefore
     *
     * @param joinPoint
     * @desc 前置拦截
     */
    @Before("RequestLog()")
    public void deBefore(JoinPoint joinPoint) {
        try {
        } catch (Exception e) {
            log.error(joinPoint.getArgs().toString());
        }
    }
    /**
     * throwss
     *
     * @param joinPoint
     * @desc 后置异常通知
     */
    @AfterThrowing("RequestLog()")
    public void throwss(JoinPoint joinPoint) {
        System.out.println("方法异常时执行....."+joinPoint.getTarget() );
    }
}