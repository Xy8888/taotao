package com.taotao.manager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMybatis {

    private ApplicationContext alc;

    //private ItemMapper itemMapper;

    @Before
    public void  init() {
        alc = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
//        itemMapper = alc.getBean(ItemMapper.class);
    }

    @Test
    public void testSpringContext() {
        for (String s : alc.getBeanDefinitionNames()) {
            System.out.println(s);
        }
    }
}
