package com.errorcode.aop.ex03;

import org.springframework.aop.framework.ProxyFactory;

import com.errorcode.aop.ex02.adv.Packaging;
import com.errorcode.aop.ex03.adv.ThrowLog;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MartClient {
    public static void main(String[] args) {
        // 1. ProxyFactory 생성
        ProxyFactory factory = new ProxyFactory();

        // 2. target을 MartImpl로 지정
        factory.setTarget(new MartImpl());

        // 3. ex02의 packaging을 advice로 지정
        factory.addAdvice(new Packaging());

        // 4. ex03의 ThrowLog을 advice로 지정
        factory.addAdvice(new ThrowLog());

        // 5. proxy 객체 생성 후 getName 호출
        try {
            ((Mart)factory.getProxy()).getProduct("깔깔유머집"); 
        } catch(RuntimeException e) {
            log.error(e.getMessage());
        }
    }
}
