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
public class ItemController {

    //@Autowired
    @Reference(interfaceClass = ItemService.class)
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "item", method = RequestMethod.POST)
    public String addItem(Item item, String desc) {
        int acount = itemService.add(item, desc);
        return "success";
    }

    @RequestMapping(method =  RequestMethod.GET, value = "/list")
    @ResponseBody
    public PageInfo<Item> getList(int page, int size) {
        PageInfo<Item> pageInfo = itemService.getPageList(page, size);
        return pageInfo;
    }
}
