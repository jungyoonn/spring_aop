package com.errorcode.aop.ex06;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class MyAdvice {
    @Pointcut("execution(* *(int)) && args(intValue)") // 조건식
    public void hello(int intValue) {}
    
    @Pointcut("bean(myDependency)")
    public void beanPointcut() {}

    // 여기까지 포인트컷 사전 등록

    @Before("hello(intValue) && beanPointcut()")
    public void simpleBefore(JoinPoint joinPoint, int intValue) { // before advice가 됨
        if(intValue > 5000) {
            log.info(joinPoint.getSignature().getName());
        }
    }

    @Around("execution(* bye(..))")
    public Object simpleAround(ProceedingJoinPoint pjp) throws Throwable{
        log.info("around before");
        Object o = pjp.proceed(); // 얘가 진짜로 할 일
        log.info("around after");
        return o;
    }

    // AfterReturnning은 정상 종료
    // AfterThrow는 예외 발생 시
    @After("execution(* bye(..))")
    public void simpleAfter() {
        log.info("myAfter");
    }
}
