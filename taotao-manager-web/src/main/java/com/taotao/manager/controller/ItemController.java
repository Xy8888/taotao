package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ItemController {

    //@Autowired
    @Reference(interfaceClass = ItemService.class)
    private ItemService itemService;

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseBody
    public String addItem(Item item, String desc) {
        int acount = itemService.add(item, desc);
        return "success";
    }

    @RequestMapping(value = "/item", method =  RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") int size) {
        PageInfo<Item> pageInfo = itemService.getPageList(page, size);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", pageInfo.getTotal());
        dataMap.put("rows", pageInfo.getList());
        return dataMap;
    }
}
