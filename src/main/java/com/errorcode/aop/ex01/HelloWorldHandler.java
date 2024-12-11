package com.errorcode.aop.ex01;

import java.lang.reflect.*;

import lombok.extern.log4j.Log4j2;;

@Log4j2
public class HelloWorldHandler<T> implements InvocationHandler { // 핸들러가 크로스컷팅 역할을 하는 것이다 (수직으로 배치된 요소들의 공통 작업을 수평으로 수행)
    private T t;
    public HelloWorldHandler(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // 얘가 실제로 일을 한다 (클라이언트를 실행했을 때)
        long start = System.currentTimeMillis();
        Object o = method.invoke(t, args);
        method.invoke(t, args); // 두 번 호출하면 sayHello 메서드가 두 번 출력된다

        log.info(System.currentTimeMillis() - start + "ms");
        return o;
    }
    
}
