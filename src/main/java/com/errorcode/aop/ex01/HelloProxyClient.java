package com.errorcode.aop.ex01;

import java.lang.reflect.Proxy;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HelloProxyClient {
    public static void main(String[] args) {
        @SuppressWarnings("rawtypes")
        Class[] arrClass = {HelloWorld.class};
        @SuppressWarnings("rawtypes")
        HelloWorldHandler handler = new HelloWorldHandler<>(new HelloWorldImpl());

        // 기존에 사용하던 패턴
        HelloWorld helloWorld = new HelloWorldImpl();
        helloWorld.sayHello("개똥이");
        log.info(helloWorld);

        log.info("============================================");

        // 프록시(대리자) 패턴
        HelloWorld proxy = (HelloWorld)Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), arrClass, handler);
        proxy.sayHello("새똥이");
        log.info(proxy); // helloWorld랑 주소값이 다르다 (프록시는 대리 역할이기 때문에 진짜 helloWorld가 아님)
    }
}
