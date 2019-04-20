package com.taotao.sso;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    private ApplicationContext alc;

    @Before
    public void init() {
        alc = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
    }

    @Test
    public void testSpring() {
        /*for (String s : alc.getBeanDefinitionNames()) {
            System.out.println(s);
        }*/

    }
}
