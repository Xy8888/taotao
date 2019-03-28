package com.taotao.manager;

import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpringMybatis {

    private ApplicationContext alc;

    private ItemService itemService;

    @Before
    public void  init() {
        alc = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        itemService = alc.getBean(ItemService.class);
    }

    @Test
    public void testSpringContext() {
        List<Item> list = itemService.getList();
        for (Item item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void testPage() {
        /*List<Item> list = itemService.getPageList(2, 2);
        for (Item item : list) {
            System.out.println(item.getTitle());
        }
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.toString());*/
        List<Item> list = itemService.getList();
        for (Item item : list) {
            System.out.println(item.getTitle());
        }
    }
}
