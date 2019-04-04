package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.manager.model.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "item")
public class ItemCatController {

    @Reference(interfaceClass = ItemCatService.class)
    private ItemCatService itemCatService;

    @RequestMapping(value = "cat", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemCat> queryItemCatByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return this.itemCatService.getItemCatByParentId(parentId);
    }

}
