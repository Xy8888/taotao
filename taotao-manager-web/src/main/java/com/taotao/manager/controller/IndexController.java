package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/page")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
