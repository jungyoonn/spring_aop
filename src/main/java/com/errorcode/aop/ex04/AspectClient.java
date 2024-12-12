package com.errorcode.aop.ex04;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.errorcode.aop.ex02.adv.Seasoning;

public class AspectClient {
    public static void main(String[] args) {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* two*(..))"); // 포인트컷의 조건식이 "" 안에 들어가게 된다
                                                            // void two(..)는 시즈닝을 함, int two(..) 는 시즈닝 안 함 (two()의 반환 타입이 void이기 때문)

        Advisor advisor = new DefaultPointcutAdvisor(pc, new Seasoning());

        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvisor(advisor);
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        ((First)factory.getProxy()).two2();
        
        factory = new ProxyFactory(new Second());
        factory.addAdvisor(advisor);
        ((Second)factory.getProxy()).one();
        ((Second)factory.getProxy()).two();
    }
}
