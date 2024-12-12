package com.errorcode.aop.ex06;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AspectJTests {
    @Autowired
    private MyBean myBean;

    @Test
    public void testBean() {
        myBean.run();
    }
}
