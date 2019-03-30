package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "item")
public class ItemController {

    //@Autowired
    @Reference
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "add")
    public String addItem(String name) throws Exception {
        Item item = new Item();
        item.setId(System.currentTimeMillis()+(long)(Math.random()*1000));
        item.setTitle(name);
        item.setSellPoint("清仓大甩卖！");
        item.setPrice(998L);
        item.setNum(10);
        item.setBarcode("No.0000000001");
        item.setImage("http://image.itcast.cn/1.jpg");
        item.setCid(14L);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        int acount = itemService.addItem(item);
        return ""+acount;
    }

    @RequestMapping(method =  RequestMethod.GET, value = "/list")
    @ResponseBody
    public PageInfo<Item> getList(int page, int size) {
        PageInfo<Item> pageInfo = itemService.getPageList(page, size);
        return pageInfo;
    }

}
