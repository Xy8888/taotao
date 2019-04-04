package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping(method = RequestMethod.GET, value = "/{pageName}")
    public String page(@PathVariable(value = "pageName")String pageName) {
        return pageName;
    }
}
